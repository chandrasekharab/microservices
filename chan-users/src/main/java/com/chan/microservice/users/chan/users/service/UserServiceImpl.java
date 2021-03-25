package com.chan.microservice.users.chan.users.service;

import com.chan.microservice.users.chan.users.data.UserEntity;
import com.chan.microservice.users.chan.users.data.UsersRepository;
import com.chan.microservice.users.chan.users.model.AlbumResponseModel;
import com.chan.microservice.users.chan.users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UsersService {

    UsersRepository usersRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    RestTemplate restTemplate;
    Environment environment;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           RestTemplate restTemplate,
                           Environment environment
                           ) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    @Override
    public UserDto createUser(UserDto userDatails) {
        userDatails.setUserId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = mapper.map(userDatails, UserEntity.class);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDatails.getPassword()));

        usersRepository.save(userEntity);

        UserDto userDto = mapper.map(userEntity, UserDto.class);

        return userDto;
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = usersRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = usersRepository.findByUserId(userId);

        if (userEntity == null) {
            throw new UsernameNotFoundException(userId);
        }

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
        String albumUrl = String.format(environment.getProperty("albums.url"), userId);

        ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate.exchange(albumUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlbumResponseModel>>() {
                });

        List<AlbumResponseModel> albumsList = albumsListResponse.getBody();
        userDto.setAlbumsList(albumsList);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByEmail(username);

        if (userEntity == null) throw new UsernameNotFoundException(username);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }
}
