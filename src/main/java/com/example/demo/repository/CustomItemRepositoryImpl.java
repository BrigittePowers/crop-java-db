package com.example.demo.repository;

import com.example.demo.models.Crop;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class CustomItemRepositoryImpl implements CustomItemRepository{
    @Autowired
    MongoTemplate mongoTemplate;

    public void updateItemCustom(String name, String newName, int newGrowthTime, int newBuyPrice, int newSellPrice,  String newSeason) {
        Query query = new Query(Criteria.where("name").is(name));
        Update update = new Update();
        update.set("name", newName);
        update.set("growthTime", newGrowthTime);
        update.set("buyPrice", newBuyPrice);
        update.set("sellPrice", newSellPrice);
        update.set("season", newSeason);

        UpdateResult result = mongoTemplate.updateFirst(query, update, Crop.class);

        if(result == null)
            System.out.println("No documents updated");
        else
            System.out.println(result.getModifiedCount() + " document(s) updated");
    }
}
