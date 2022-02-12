package com.example.demo;

import com.example.demo.models.Crop;
import com.example.demo.repository.CustomItemRepository;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories

public class MdbSpringBootApplication implements CommandLineRunner {

    @Autowired
    ItemRepository cropRepo;

    @Autowired
    CustomItemRepository customRepo;

    public static void main(String[] args) {
        SpringApplication.run(MdbSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("------ SEEDING DATABASE ------");
        createCropItem();

        System.out.println("------ SHOW ALL CROPS ------");
        showAllCrops();

        System.out.println("------ CHECK OUT THIS PARSNIP YO ------");
        getCropByName("Parsnip");

        System.out.println("------ CROPS IN SPRING ------");
        getCropsBySeason("Spring");

        System.out.println("------ CROPS IN SUMMER ------");
        getCropsBySeason("Summer");

        System.out.println("------ LETS CHANGE PARSNIPS JUST CUZ ------");
        updateItemCustom("Parsnip", "Turnip", 1, 100, 140, "Spring");

        System.out.println("------ HAHA BAI POTATO ------");
        deleteCrop("2");

        System.out.println("------ FINAL COUNT OF CROPS ------");
        findCountOfCrops();

        System.out.println("------ BAIIII (b ^_^)==CD ------");
    }

    //CREATE
    void createCropItem() {
        System.out.println("Creating crop data...");
        cropRepo.save(new Crop("1", "Parsnip", 5, 50, 80, "Spring"));
        cropRepo.save(new Crop("2", "Potato", 8, 80, 120, "Spring"));
        cropRepo.save(new Crop("3", "Tomato", 12, 50, 110, "Summer"));
        cropRepo.save(new Crop("4", "Melon", 12, 80, 160, "Summer"));
        cropRepo.save(new Crop("5", "Starfruit", 12, 400, 1200, "Summer"));
        System.out.println("...Crop seeding complete.");
    }

    // READ
        //1. Show all the data
        public void showAllCrops() {
            cropRepo.findAll().forEach(item -> System.out.println(getItemDetails(item)));
        }

        //2. Get item by name
        public void getCropByName(String name) {
           System.out.println("Getting item by name: " + name);
           Crop item = cropRepo.findItemByName(name);
           System.out.println(getItemDetails(item));
        }

        //3. Get name and growth time of all items in category
        public void getCropsBySeason(String season) {
            System.out.println("Getting crops for the season: " + season);
            List<Crop> list = cropRepo.findAllBySeason(season);

            list.forEach(item -> System.out.println(
                    "Name: " + item.getName() +
                    ", Growth: " + item.getSeason() + " days"
            ));
        }

        //4. Get count of items in the collection
        public void findCountOfCrops() {
            long count = cropRepo.count();
            System.out.println("There are " + count + " crops in this collection.");
        }

        //Format helper
        public String getItemDetails(Crop item) {
            System.out.println(
                    "Item Name: " + item.getName() +
                            ", \nGrowth: " + item.getGrowthTime() +
                            ", \nSeason: " + item.getSeason() +
                            ", \nPrice: " + item.getBuyPrice() +
                            ", \nSells for: " + item.getSellPrice()
            );

            return "";
        }

        // UPDATE WITH MONGO REPO
//        public void updateCropByName(String name, String newName, int newGrowthTime, int newBuyPrice, int newSellPrice,  String newSeason) {
//
//            //Find the item
//            Crop item = cropRepo.findItemByName(name);
//
//            //Update the item
//            item.setName(newName);
//            item.setGrowthTime(newGrowthTime);
//            item.setBuyPrice(newBuyPrice);
//            item.setSellPrice(newSellPrice);
//            item.setSeason(newSeason);
//
//            //Save to db
//            cropRepo.save(item);
//
//            System.out.println("Updated " + name + " information");
//            getCropByName(newName);
//
//        }

    // UPDATE WITH CUSTOM REPO
    public void updateItemCustom(String name, String newName, int newGrowthTime, int newBuyPrice, int newSellPrice,  String newSeason) {
        System.out.println("Updating " + name);
        customRepo.updateItemCustom(name, newName, newGrowthTime,newBuyPrice, newSellPrice, newSeason);
    }

        //DELETE
        public void deleteCrop(String id) {
            cropRepo.deleteById(id);
            System.out.println("Item #" + id + " deleted.");
        }
}
