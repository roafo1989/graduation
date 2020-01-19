package com.example.grad1.service.voice;

import com.example.grad1.domain.Restaurant;
import com.example.grad1.domain.Voice;
import com.example.grad1.repository.RestaurantRepository;
import com.example.grad1.repository.UserRepository;
import com.example.grad1.repository.VoiceRepository;
import com.example.grad1.to.voiceTo.VoiceTo;
import com.example.grad1.to.voiceTo.VoiceUtil;
import com.example.grad1.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class VoiceServiceImpl implements VoiceService {

    private final VoiceRepository voiceRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Autowired
    public VoiceServiceImpl(VoiceRepository voiceRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.voiceRepository = voiceRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Voice create(int userId, int restaurantId) {
        LocalDateTime dateTime = LocalDateTime.now();
        List<Voice> voices = voiceRepository.getMyVoice(dateTime.with(LocalTime.MIN),dateTime.with(LocalTime.MAX),userId);
        if(voices.isEmpty()){
            Voice created = new Voice(dateTime,restaurantRepository.getOne(restaurantId),userRepository.getOne(userId));
            return voiceRepository.save(created);
        } else {
            ValidationUtil.checkTimeForOperations(dateTime.toLocalTime());
            Voice voice = voices.get(0);
            voice.setRestaurant(restaurantRepository.getOne(restaurantId));
            voice.setDateTime(dateTime);
            return voiceRepository.save(voice);
        }
    }

    //By User
    @Override
    public List<VoiceTo> getByUserIdAndDate(int userId, String dateStr) {
        LocalDateTime date = LocalDate.parse(dateStr).atStartOfDay();
        return VoiceUtil.asTo(voiceRepository.getMyVoice(date.with(LocalTime.MIN),date.with(LocalTime.MAX),userId));
    }
    @Override
    public List<VoiceTo> getAllByUserId(int userId) {
        return VoiceUtil.asTo(voiceRepository.findByUserIdOrderByDateTimeDesc(userId));
    }

    //By Restaurant
    @Override
    public List<VoiceTo> getByRestaurantIdAndDate(String dateStr, int restaurantId) {
        LocalDateTime date = LocalDate.parse(dateStr).atStartOfDay();
        return VoiceUtil.asTo(voiceRepository.getAllByRestaurantIdAndDate(date.with(LocalTime.MIN),date.with(LocalTime.MAX),restaurantId));
    }


    @Override
    public List<VoiceTo> getByRestaurantBetweenDates(String startDate, String endDate, int restaurantId){
        LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
        LocalDateTime end = LocalDate.parse(endDate).atStartOfDay();
        return VoiceUtil.asTo(voiceRepository.getAllByRestaurantIdAndDate(start.with(LocalTime.MIN),end.with(LocalTime.MAX),restaurantId));
    }

    @Override
    public List<VoiceTo> getByRestaurantId(int restaurantId){
        return VoiceUtil.asTo(voiceRepository.getAllByRestaurantId(restaurantId));
    }


    //Summary
    @Override
    public Map<Restaurant, List<VoiceTo>> getAllByRestaurantIdAndDate(String date) {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        Map<Restaurant,List<VoiceTo>> voiceResult = new ConcurrentHashMap<>();
        for(Restaurant r : restaurantList){
            List<VoiceTo> voiceList = getByRestaurantIdAndDate(date,r.id);
            voiceResult.put(r,voiceList);
        }
        return voiceResult;
    }
    @Override
    public Map<Restaurant, List<VoiceTo>> getAllByRestaurantId() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        Map<Restaurant,List<VoiceTo>> voiceResult = new ConcurrentHashMap<>();
        for(Restaurant r : restaurantList){
            List<VoiceTo> voiceList = getByRestaurantId(r.id);
            voiceResult.put(r,voiceList);
        }
        return voiceResult;
    }
    @Override
    public Map<String, Integer> getRatingByDate(String date) { ;
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        Map<String, Integer> voiceResult = new ConcurrentHashMap<>();
        for(Restaurant r : restaurantList){
            List<VoiceTo> voiceList = getByRestaurantIdAndDate(date,r.id);
            voiceResult.put(r.getName(),voiceList.size());
        }
        return voiceResult;
    }
    @Override
    public Map<String, Integer> getRating() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        Map<String, Integer> voiceResult = new ConcurrentHashMap<>();
        for(Restaurant r : restaurantList){
            List<VoiceTo> voiceList = getByRestaurantId(r.id);
            voiceResult.put(r.getName(),voiceList.size());
        }
        return voiceResult;
    }
    @Override
    public List<VoiceTo> getAllByDate(String dateStr) {
        LocalDateTime date = LocalDate.parse(dateStr).atStartOfDay();
        return VoiceUtil.asTo(voiceRepository.findAllByDate(date.with(LocalTime.MIN),date.with(LocalTime.MAX)));
    }
    @Override
    public List<VoiceTo> getAll() {
        return VoiceUtil.asTo(voiceRepository.findAll());
    }
}
