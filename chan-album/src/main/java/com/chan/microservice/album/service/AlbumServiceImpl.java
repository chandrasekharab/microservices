package com.chan.microservice.album.service;

import com.chan.microservice.album.data.AlbumEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Override
    public List<AlbumEntity> getAlbums(String userId) {

        List<AlbumEntity> albums = new ArrayList<>();

        AlbumEntity albumEntity1 = new AlbumEntity();
        albumEntity1.setUserId(userId);
        albumEntity1.setAlbumId("album id");
        albumEntity1.setId(1L);
        albumEntity1.setDescription("album descpription");
        albumEntity1.setName("album name");

        AlbumEntity albumEntity2 = new AlbumEntity();
        albumEntity2.setUserId(userId);
        albumEntity2.setAlbumId("album id 2");
        albumEntity2.setId(2L);
        albumEntity2.setDescription("album descpription 2");
        albumEntity2.setName("album name 2");

        albums.add(albumEntity1);
        albums.add(albumEntity2);
        return albums;
    }
}
