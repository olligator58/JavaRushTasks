package com.javarush.task.task24.task2406;

import java.math.BigDecimal;

/* 
Наследование от внутреннего класса
*/

public class Solution {
    public class Building {
        public class Hall {
            private BigDecimal square;

            public Hall(BigDecimal square) {
                this.square = square;
            }
        }

        public class Apartments {
        }
    }

    public class BigHall extends Building.Hall {
        public BigHall(Building building, BigDecimal square) {
            building.super(square);
        }
    }

    public class Apt3Bedroom extends Building.Apartments {
        public Apt3Bedroom() {
            new Building().super();
        }
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        BigHall bigHall = s.new BigHall(s.new Building(), new BigDecimal("50"));
        System.out.println(bigHall);
        Apt3Bedroom apt3Bedroom = s.new Apt3Bedroom();
        System.out.println(apt3Bedroom);

    }
}
