/*
 *Taka Masuda
 *CPSC-330
 *Dr. Anewalt
 *Project 1: Emergency Room
 *This projecjt simulates an emergency room by
 *utilizing an ArrayList as the waiting list and allows you
 *to enter a patient, remove the next patient with highest 
 *priority, determine the position of a patient and print the list.
*/
import java.util.*;

public class main
{		
	public static void main(String[]	args)
	{	 
		Scanner read =	new Scanner(System.in);//user input object
		ArrayList<patient> myList = new ArrayList<patient>();//arrayList
      
		char selection	= ' ';//for menu selection
		int count =	0; //for tracking count of menu selections
      int i = 0; //used to run through list and insert patient in correct order
      boolean inRightPlace	= false; //correct position in list
      		
		while((selection!='q')&&(selection!='Q'))//While menu choice is not to Quit
		{
			selection =	menu();//Generate Menu
	  
			if((selection=='e')||(selection=='E'))
			{  
            //Enter New Patient
            i = 0;
				patient guest = new patient();
				if(count==0)
				{
               //First Patient of the Day
  				   System.out.print("Enter the patient's first and last name (EX. John Doe) ");
	   			guest.firstName = read.next();
               guest.lastName = read.next();
               guest.firstName = guest.firstName.toUpperCase();
               guest.lastName = guest.lastName.toUpperCase();
               guest.fullName = guest.firstName + " " + guest.lastName;	
               read.nextLine();
			   	System.out.print("\nEnter the patient's insurance (EX. Anthem) ");
   				guest.insurance =	read.nextLine();
          		System.out.print("\nWhat time did the patient arrive (Military Time) ");
   				guest.time = read.nextLine();
   				System.out.print("\nWhat is the patient's priority level (EX. 1-100) ");
   				guest.priorityNum	= read.nextInt();
               while((guest.priorityNum<1)||(guest.priorityNum>100))
               {
                  System.out.println("\nPlease enter a priority level from 1-100");
                  guest.priorityNum = read.nextInt();
               }
   				read.nextLine();
   				count++;
   				myList.add(guest);
				}
				else if(count>=1)
				{	
               //Second Patient of the Day and counting
   				System.out.print("Enter the patient's first and last name (EX. John Doe) ");
   				guest.firstName = read.next();
               guest.lastName = read.next();
               guest.firstName = guest.firstName.toUpperCase();
               guest.lastName = guest.lastName.toUpperCase();
               guest.fullName = guest.firstName + " " + guest.lastName;	
               read.nextLine();		  
   				System.out.print("\nEnter the patient's insurance (EX. Anthem) ");
   				guest.insurance =	read.nextLine();
   				System.out.print("\nWhat time did the patient arrive (Military Time) ");
   				guest.time = read.nextLine();
   				System.out.print("\nWhat is the patient's priority level (EX. 1-100) ");
   				guest.priorityNum	= read.nextInt();
               while((guest.priorityNum<1)||(guest.priorityNum>100))
               {
                  System.out.println("\nPlease enter a priority level from 1-100");
                  guest.priorityNum = read.nextInt();
               }
   				read.nextLine();
   				count++;
   				inRightPlace = false;
              
   				while(!inRightPlace)     
               {
                  //Insert into Correct Position
                  if(guest.priorityNum > myList.get(i).priorityNum)//NumberToBeInserted greater than CurrentlyLooping 
                  {
                     myList.add(i, guest);
                     inRightPlace = true;
                  }
                  else if((guest.priorityNum < myList.get(i).priorityNum)&&(i==myList.size()-1))//last place of list
                  {
                     myList.add(i+1, guest);
                     inRightPlace = true;
                  }
                  else if(guest.priorityNum == myList.get(i).priorityNum)//if equal, check times
                  {
                     int equality;
                     equality = guest.time.compareTo(myList.get(i).time);//check time
                     int guestNameSize, listNameSize;//for time lengths
                     guestNameSize = guest.time.length();
                     listNameSize = myList.get(i).time.length();
                     
                     if(guestNameSize==listNameSize)//time length is the same
                     {
                        if(equality<0)//guest time is before currently looping time
                        {
                           myList.add(i, guest);
                           inRightPlace = true; 
                        }
                     }
                     else if(guestNameSize<listNameSize)//guest time is before currently looping time
                     {
                        myList.add(i, guest);
                        inRightPlace = true;
                     }
                     
                     if(i==myList.size()-1)//end of list
                     {
                        myList.add(i+1, guest);
                        inRightPlace = true;
                     }
                    
                                    
                                                           
                     
                  }
               
                  i++;   
               }                                                                             
   				  
				}//end count>=1
            
 			}
			else if((selection=='n')||(selection=='N'))
			{
			   //Next Patient
            if(!myList.isEmpty())
            {
               System.out.println("\n\nThe patient " + myList.get(0).fullName + " has been seen.\n");
               myList.remove(0);
              
            }
            else
            {
               System.out.println("\n\nThere is no patient waiting to be seen.\n");
            }
			}
			else if((selection=='p')||(selection=='P'))
			{
			   //Determine Position of Patient
            System.out.println("\n\nSearch the position for a patient by typing their last name followed by their first name.\n");
            String first, last;
            last = read.next();
            first = read.next();
            last = last.toUpperCase();
            first = first.toUpperCase();
            boolean nameFound = false;
                       
            for(int y = 0; y < myList.size(); y++)
            {               
               int isEqual, isEqual1;
               
               isEqual = last.compareTo(myList.get(y).lastName);
               isEqual1 = first.compareTo(myList.get(y).firstName);
               
               if((isEqual==0)&&(isEqual1==0))
               {
                  nameFound = true;
                  System.out.println("\n\nYour position from the front of the line is: " + (y+1));
               }
              
            }
            if(!nameFound)
               System.out.println("\nThe patient name you searched is not in the current waiting list \n");
            
            
			}
			else if((selection=='d')||(selection=='D'))
			{
            //Print List      
			   System.out.println("\n          ***CURRENT WAITING LIST***             \n");
            System.out.println("PATIENT | PRIORITY-NUM | INSURANCE | TIME-IN \n");
            for(int x = 0; x<myList.size(); x++)
               System.out.println(myList.get(x).fullName + "  |  " + myList.get(x).priorityNum + "  |  " + myList.get(x).insurance + "  |  " + myList.get(x).time + 
                                 "\n");
			}
			else if((selection=='q')||(selection=='Q'))
			{
			   //Quit
			}
		}		
	
	
	}
	static char	menu()
	{
		char response;
		Scanner in = new Scanner(System.in);
		System.out.println ("\nPlease select your option from the following menu: ");
		System.out.println("E: Enter a new patient");
		System.out.println("N: Find next patient and remove him/her from the list");
		System.out.println("P: Determine the position of a specific patient");
		System.out.println("D: Print the list of patients");
		System.out.println("Q: Quit");
		System.out.print("\nEnter your choice: ");
		response	= in.next().charAt(0);
		
		while((response!='E')&&(response!='e')&&(response!='N')&&(response!='n')&&(response!='P')&&(response!='p')&&(response!='D')&&(response!='d')&&(response!='Q')&&(response!='q'))
		{
			System.out.print("That is an invalid choice, Please enter either E / N / P / D / Q ");
			response	= in.next().charAt(0);
		}
		return response;	
	}
  
}