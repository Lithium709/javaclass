package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookingReviews {



    static String[] getReviews(){
        String[] reviews = {"The bamboo house is as stunning and interesting in person as it is in the photos. A must-do! We had a great" +
                " stay and enjoyed riding a motorbike to the local sites (rice terrace, rice fields, waterfalls, etc). We also enjoyed the pool and we took a nice walk through the \" +\n" +
                "village across the river. The only slight negative is that it's " +
                "very hot as only one bed has an AC and it doesn't work very well. But if you're considering staying, you won't regret it! ",
                            "Sunrise house is an amazing home, most beautiful architecture one can imagine. Pure magic. The Green Village, where the house is " +
                                    "located, is a beautiful place filled with the most lovely people, delicious food, and incredible hospitality. They took" +
                                    " care of our every need, from taxis to dinner reservations to even picking up our lost luggage from the airport. Our" +
                                    " children took a bamboo model making workshop and we learned to make a play the jegog. Beside the amazing architecture," +
                                    " there is so much to experience at Green Village. I cannot say enough positive things about the magic of our experience " +
                                    "at this rental.",
        "We are glad we spent the 2 nights and the money to experience this amazing structure.  It is a bit buggy being an open air design next to a river valley and the privacy is pretty minimal, but still worth a visit.",
        "Amazing once in a lifetime experi" +
                "ence staying in this unique home. I" +
                "t was absolutely stunning. We greatly enjoyed our two night stay. I highly recommend the property. Do be aware that t" +
                "he house is indeed one with nature meaning that bugs, butterflys, birds, and lizards will also be your fellow guests! The " +
                "masterbedroom had an AC unit under the bug net which made sleeping quite comfortable.",
        "Green Village is out of this world. I could not have dreamt up a better start to my honeymoon. Thank you!!",
         "The bathrooms are also not very private and sound travels so be prepared to be very cozy with your housemates despite all the space. The downstairs shower (in the only room with an A/C) was temperamental and alternately froze and scalded the bather. The showers in the other 2 rooms worked perfectly. ",
        "This place is incredible - I've stayed a lot of places around the world and this one is truly special.",
         "The kids really enjoyed the plunge pool. I probably wouldn't recommend the house for adventurous toddlers, as there are no windows, and it's pretty high up, but for older kids it is like a giant tree house." +
                 " We also did a tour of the nearby bamboo factory and the Green School which I would recommend" +
                 " The staff were helpful and friendly and organised for dinners to be delivered for us each night.",
         "There is always security within the village and I never felt like any question was too much trouble for the staff. It was great to meet Andrew and hear a little background on the Bamboo Village itself. " +
                 " I will certainly be recommending this residence to family and friends and would love to stay here again in the near future.",
                "Felt like an alternate reality! Surreal and liberating. Julianne was very witty and incredibly soothing. The event was very fun. My first yoga",
                "What a wonderful apartment and it was everything as advertised!! Bright, airy and comfortable along with a great central location are the highlights." +
                        " Above all bathroom and made our check in process very simple and efficient."
        };
        return reviews;

    }

    // Get list of hotels by keyword relevancy
   // int[] getReviewList(String keyWord, int[] hotel_ids, String[] reviews){


   // }


    public static void main(String[] a) {

        int[] hotelIds = new int[]{43,55,21,84,32,2,4,78,8,65,74};
        final String[] reviews = getReviews();
        String keyWords = "motorbike pool bamboo";
        int [] hotelsRate = new int[11];
        // Arrays.stream(keyWords.split(" ")).forEach(System.out::println);
        for(int i=0;i< reviews.length;i++){
           for(String key: keyWords.split(" ")) {
               if(reviews[i].contains(key)){
                   hotelsRate[i]++;
               }
           }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<11;i++){
            map.put(hotelIds[i],hotelsRate[i]);
        }
        int[] rating = map.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).limit(3)
                .mapToInt((e)->e.getValue()).toArray();

        for(int r:rating){
            System.out.println(r);
        }
        //System.out.println( map.values());

    }
}
