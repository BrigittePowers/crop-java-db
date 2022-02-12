package com.example.demo.repository;

import com.example.demo.models.Crop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<Crop, String> {

    @Query("{name:'?0'}")
    Crop findItemByName(String name);

    @Query(value="{season:'?0'}", fields="{'name' : 1, 'growthTime' : 1, 'buyPrice' : 1, 'sellPrice' : 1, 'season' : 1}")
    List<Crop> findAllBySeason(String season);

    public long count();

}
