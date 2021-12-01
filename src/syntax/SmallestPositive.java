package syntax;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SmallestPositive {


    static class City{
        int number;
        List<City> neighbors = new ArrayList<>();

        @Override
        public String toString() {
            return "City{" +
                    "number=" + number +
                    '}';
        }
    }

    public static  void main(String arg[]){

        int T[] = new int[10];
        City[] cities = new City[10];
        for(int i=0; i<T.length; i++){
            cities[i] = new City();
            cities[i].number=i;
        }
        T[0]=9;
        T[1]=1;
        T[2]=4;
        T[3]=9;
        T[4]=0;
        T[5]=4;
        T[6]=8;
        T[7]=9;
        T[8]=0;
        T[9]=1;
        City capital = null;
        for(int i=0; i<T.length; i++){
            if(i != T[i]){
               cities[T[i]].neighbors.add(cities[i]);
            }else {
                capital = cities[i];
            }
         }

        int distances[] = new int[10];

        Set<City> visited = new HashSet<>();

        findDistances(capital, distances, visited,0);

        for(int i=0; i<distances.length; i++) {
            System.out.println(distances[i]);
        }
    }

    static void findDistances(City city, int[] distances, Set<City> visited, int depth){
        if(depth == distances.length){
            return;
        }
        if(!visited.add(city)){
           return;
        }

        distances[depth] += city.neighbors.size();
        for(City c: city.neighbors){
           findDistances(c, distances, visited, depth+1);
        }

    }

}
