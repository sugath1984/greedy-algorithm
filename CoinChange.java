import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author sugath
 */
public class CoinChange {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {        
       double value; 
       int realAmount = 0;
       Scanner scan = new Scanner( System.in );
       System.out.print("Enter a double:");
       value = scan.nextDouble();
       
       int[] denominations = { 7, 1, 2, 5, 10, 25, 50, 100 };//1 st => size of the list
       int[] coins = new int[denominations.length];//Store the # of coins that each denominator
       
       System.out.println(".....................Result........................");
       System.out.println("Dynamic changing values :");
       //Calculate the minimum # of units in dynamic
       dynamicChanges((int)value, coins, denominations);
       
        //Show the final output of dynamic algorithm
       showdynamicResult(denominations, coins);
       
       System.out.println("Greedy changing values :");
       //Calculate the minimum # of units in greedy
       greedyChanges((int)value, coins, denominations);
       //Show the final output of greedy algorithm
       showgreedyResult(denominations, coins);        
       
       System.out.println("....................................................");
        // TODO code application logic here
    }
    
     //Calculate the minimum number of units for given amount (Dynamic Strategy)
    public static void dynamicChanges(int amount, int[] coin, int[] denomination)
    {
         int n = denomination.length -1;
         int [][] table = new int[n+1][]; //Tabular format (Table for dynamic approach)
         for (int counter = 0; counter < table.length; counter++)
                table[counter] = new int[amount + 1];
         
         int j, k;
            //base case for tabular format
            for ( k = 0; k <= amount; k++ )
                table[0][k] = 0;
            //Tabular Format generating
            
         for ( j = 1; j <= n; j++ )
            { 
                coin[j] = 0; // zeroes in the coins-used vector
                table[j][0] = 0; // column 0 holds zeroes.
                for ( k = 1; k <= amount; k++ )
                    if ( j == 1 )
                        if ( k < denomination[j] )
                            table[j][k] = Integer.MAX_VALUE / 2; // restrict the overflow
                        else
                            table[j][k] = 1 + table[j][k - denomination[j]];
                    else
                        if ( k < denomination[j] )
                            table[j][k] = table[j - 1][k];
                        else//Take the min value
                            table[j][k] = Math.min(table[j - 1][k], 1 + table[j][k - denomination[j]]);
            } 
         
         // Start out walking the table
            j = n; k = amount;
            //Get the coins used from the table
            while ( k > 0 && j > 0 )
                if (table[j][k] == table[j - 1][k]) // denomination not used
                    j = j - 1;
                else // denomination was used
                { ++coin[j]; k = k - denomination[j]; }
    }
    
    //Display the final output on the Console (Dynamic Results)
    public static void showdynamicResult(int[] denominations, int[] coins) 
    {
        for (int i = 1; i < coins.length; i++) {
            if(coins[i]>0)
            {
               System.out.println("Number of " + denominations[i] + "s : " + coins[i]); 
            }    

        }
    }

    //Calculate the minimum number of units for given amount (Greedy Strategy)
    private static void greedyChanges(int amount, int[] coin, int[] denomination) {
        int counter;
        Arrays.sort (denomination, 1, denomination.length-1);
        //Array.Sort(denomination, 1, denomination.length-1);
        for (int i = 0; i < coin.length; i++) {
            coin[i] = 0;
        }
        for (counter = coin.length - 1; counter >= 0 & amount > 0; counter--) {
            coin[counter] = amount / denomination[counter];
            amount -= coin[counter] * denomination[counter];
        }
    }
        
    //Display the final output on the Console (Greedy Results)
    private static void showgreedyResult(int[] denominations, int[] coins)
    {
        for (int i = 1; i < coins.length; i++)
        {
            if (coins[i] > 0)
                System.out.println("Number of " + denominations[i] + "s : " + coins[i]); 

        }
    }
    
}
