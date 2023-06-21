package com.example.testproject1.service;

import com.example.testproject1.data.entity.ListenerEntity;

public interface ListenerService {

    ListenerEntity getEntity(Long id);
    void saveEntity(ListenerEntity listenerEntity);
    void updateEntity(ListenerEntity listenerEntity);
    void removeEntity(ListenerEntity listenerEntity);
}
