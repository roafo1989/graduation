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
        Voice oldVoice = voiceRepository.getMyVoice(dateTime.with(LocalTime.MIN),dateTime.with(LocalTime.MAX),userId);
        Voice created = new Voice(dateTime,restaurantRepository.getOne(restaurantId),userRepository.getOne(userId));
        ValidationUtil.checkTimeForOperations(dateTime.toLocalTime());
        if(oldVoice != null){
            voiceRepository.delete(oldVoice);
        }
        return voiceRepository.save(created);
    }

    //By User
    @Override
    public Voice getByUserId(int userId, LocalDateTime dateTime) {
        return (voiceRepository.getMyVoice(dateTime.with(LocalTime.MIN),dateTime.with(LocalTime.MAX),userId));
    }
    @Override
    public List<VoiceTo> getAllByUserId(int userId) {
        return VoiceUtil.asTo(voiceRepository.findByUserIdOrderByDateTimeDesc(userId));
    }

    //By Restaurant
    @Override
    public List<VoiceTo> getByRestaurantIdAndDate(LocalDateTime date, int restaurantId) {
        return VoiceUtil.asTo(voiceRepository.getAllByRestaurantIdAndDate(date.with(LocalTime.MIN),date.with(LocalTime.MAX),restaurantId));
    }
    @Override
    public List<VoiceTo> getByRestaurantBetweenDates(String startDate, int restaurantId){
        LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
        LocalDateTime end = LocalDateTime.now();
        return VoiceUtil.asTo(voiceRepository.getAllByRestaurantIdAndDate(start.with(LocalTime.MIN),end.with(LocalTime.MAX),restaurantId));
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
    public Map<Restaurant, List<VoiceTo>> getAllByRestaurantIdAndDate(LocalDateTime date) {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        Map<Restaurant,List<VoiceTo>> voiceResult = new ConcurrentHashMap<>();
        for(Restaurant r : restaurantList){
            List<VoiceTo> voiceList = getByRestaurantIdAndDate(date,r.id);
            voiceResult.put(r,voiceList);
        }
        return voiceResult;
    }
    @Override
    public Map<String, Integer> getRatingByDate(LocalDateTime date) {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        Map<String, Integer> voiceResult = new ConcurrentHashMap<>();
        for(Restaurant r : restaurantList){
            List<VoiceTo> voiceList = getByRestaurantIdAndDate(date,r.id);
            voiceResult.put(r.getName(),voiceList.size());
        }
        return voiceResult;
    }
    @Override
    public List<VoiceTo> getAllByDate(LocalDateTime dateTime) {
        return VoiceUtil.asTo(voiceRepository.findAllByDate(dateTime.with(LocalTime.MIN),dateTime.with(LocalTime.MAX)));
    }
}
