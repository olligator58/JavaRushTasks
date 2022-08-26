package com.javarush.task.task14.task1408;

/* 
Куриная фабрика
*/

public class Solution {
    public static void main(String[] args) {
        Hen hen = HenFactory.getHen(Country.BELARUS);
        System.out.println(hen.getCountOfEggsPerMonth());
        System.out.println(new RussianHen().getDescription());
        System.out.println(new UkrainianHen().getDescription());
        System.out.println(new BelarusianHen().getDescription());
        System.out.println(new MoldovanHen().getDescription());
    }

    static class HenFactory {

        static Hen getHen(String country) {
            Hen hen = null;
            switch (country) {
                case Country.RUSSIA:
                    hen = new RussianHen();
                    break;
                case Country.UKRAINE:
                    hen = new UkrainianHen();
                    break;
                case Country.BELARUS:
                    hen = new BelarusianHen();
                    break;
                case Country.MOLDOVA:
                    hen = new MoldovanHen();
            }
            return hen;
        }
    }
}
