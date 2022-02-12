package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public interface CustomItemRepository {


    void updateItemCustom(String name, String newName, int newGrowthTime, int newBuyPrice, int newSellPrice,  String newSeason);
}
