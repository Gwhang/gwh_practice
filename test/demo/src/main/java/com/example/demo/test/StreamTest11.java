package com.example.demo.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * stream 根据指定属性去重
 */
public class StreamTest11 {

    public static void main(String[] args) {

        List<Hero> heroList = getHeroList();
        System.out.println(heroList);
        System.out.println("-------------------------------------------------");
        ArrayList<Hero> collect = heroList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(Hero::getName))), ArrayList::new));

        System.out.println(collect);


    }

    public static List<Hero> getHeroList(){
        List<Hero> heroList = new ArrayList<Hero>();
        Hero hero1=Hero.builder().name("宋江").age("35").numberOfWins(100).build();
        Hero hero2=Hero.builder().name("宋江").age("35").numberOfWins(99).build();
        Hero hero3=Hero.builder().name("宋江").age("35").numberOfWins(98).build();
        Hero hero4=Hero.builder().name("卢俊义").age("38").numberOfWins(103).build();
        Hero hero5=Hero.builder().name("武松").age("30").numberOfWins(88).build();

        heroList.add(hero1);
        heroList.add(hero2);
        heroList.add(hero3);
        heroList.add(hero4);
        heroList.add(hero5);

        return heroList;
    }


}


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Hero{

    private String name;

    private String age;

    private int numberOfWins;


}

