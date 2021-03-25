package com.chan.microservice.album.service;

import com.chan.microservice.album.data.AlbumEntity;

import java.util.List;

public interface AlbumService {
    List<AlbumEntity> getAlbums(String userId);

}
