import javafx.application.Platform;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public
class EHDemo
{
    public static
    void main(String[] args)
    {
        int result=0;
        try
     {
         result = divide();

     }
     catch(ArithmeticException | NoSuchElementException a)
     {
         System.out.println(a.toString());
     }
        System.out.println(result);
    }

    private static
    int divide()
    {
        int x=0, y=0;


        try
        {
            x = getInt();
            y = getInt();
            System.out.println("x is " + x + ", y is " + y );
            System.out.print(x + "/" + y + " = ");

            return x/y;

        }
        catch(NoSuchElementException n)
        {
            throw new NoSuchElementException("\nUser Opted Exit");
        }
        catch(ArithmeticException a)
        {
            throw new ArithmeticException("\nAttempted to divide by Zero");
        }

    }

    private static
    int getInt()
    throws NoSuchElementException
    {
        int     x = 0;
        Scanner s = new Scanner(System.in);
        System.out.print("Please enter an intger: ");
        boolean isString = true;
        while (isString)
        {
            try
            {
                x = s.nextInt();
                s.nextLine();
                isString = false;
            }
            catch (InputMismatchException i)
            {
                System.out.println();
                System.out.print("Input requires an integer value, please try again: ");
                s.nextLine();
            }
        }
        return x;
    }

    public static
    int createErrorDivByZero(int x, int y)
    {
        try
        {
            return x/y;
        }
        catch(ArithmeticException a)
        {
//            a.printStackTrace();
            return 0;
        }

    }
}
