import java.lang.*;
import java.util.*;

//Datbase table or Schema
class Student
{
    public int RID;
    public String Name;
    public int Salary;

    //private static int Generator = 0;
    private static int Generator;   
    static              //Static block 
    {
        Generator = 0;
    }

    public Student(String str, int value)  //Parameterised constructore
    {
        this.RID = ++Generator;     //This will automatically count the data
        this.Name = str;
        this.Salary = value;
    }

    public void DisplayData()     //For display data
    {
        System.out.println(this.RID + "\t" + this.Name +"\t" + this.Salary);
    }
}

class DBMS
{
    public  LinkedList <Student> lobj = new LinkedList<>();   //here,LL is characteristics

    public DBMS()   //constructor
    {
        lobj = new LinkedList<>();   //created obj of LL
    }

    public void StartDBMS()
    {
        Scanner scanobj = new Scanner(System.in);

        System.out.println("Marvellous customised DBMS started succesfully....");
        String Query = "";

        while(true)
        {
            System.out.print("Marvellous DBMS console >> ");
            Query = scanobj.nextLine();

            String tokens[] = Query.split(" ");
            int QuerySize = tokens.length;

            if(QuerySize == 1)
            {
                if("Help".equals(tokens[0]))
                {
                    System.out.println("This application is used to demonstrates the customised DBMS");
                    System.out.println("Exit             : Terminate DBMS");
                    System.out.println("Display all data : select * from student");
                    System.out.println("Insert data      : Insert into student Name Salary");
                    System.out.println("Counting data    : select Count from student");
                    System.out.println("Maximum salary   : select Max from student");
                    System.out.println("Minimum salary   : select Min from student");
                    System.out.println("Sum of salary    : select Sum from student");
                    System.out.println("Average  salary  : select Avg from student");
                }
                else if("Exit".equals(tokens[0]))
                {
                    System.out.println("Thank you for using Marvellous DBMS!!!");
                    break;
                }
            }
            else if(QuerySize == 4)
            {
                if("select".equals(tokens[0]))
                {
                    //select * from student  == 4
                    if("*".equals(tokens[1]))
                    {
                        DisplayAll();
                    }
                    //select Count from student
                    else if("Count".equals(tokens[1]))
                    {
                        AggregateCount();
                    }
                    //select Min from student
                    else if("Min".equals(tokens[1]))
                    {
                        AggregateMin();
                    }
                    //select Max from student
                    else if("Max".equals(tokens[1]))
                    {
                        AggregateMax();
                    }
                    //select Avg from student
                    else if("Avg".equals(tokens[1]))
                    {
                        AggregateAverage();
                    }
                    //select Sum from student
                    else if("Sum".equals(tokens[1]))
                    {
                        AggregateSum();
                    }
                }
                else
                {
                    System.out.println("Please enter correct query!!!");
                }
            }
            else if(QuerySize == 5)
            {
                //Insert into student Piyush 1000  
                if("Insert".equals(tokens[0]))
                {
                    InsertData(tokens[3],Integer.parseInt(tokens[4]));
                }
            }

        }
    }

    //For Inserting Data
    public void InsertData(String str, int value)
    {
        Student sobj = new Student(str, value);
        lobj.add(sobj);
    }

    //Display All without condition
    public void DisplayAll()
    {
        for(Student sref : lobj)
        {
            sref.DisplayData();  //yatun ek ek yacha data display hoil
        }
    }

    //Display by RId
    public void DisplaySpecific(int rid)
    {
        for(Student sref : lobj)
        {
            if(sref.RID == rid)
            {
                sref.DisplayData();
                break;
            }  
        }
    }

    //Display by name
    public void DisplaySpecific(String str)   //Function overloading by changing parameter
    {
         for(Student sref : lobj)
        {
            if(str.equals(sref.Name))
            {
                sref.DisplayData();
            }
        }
    }

    //Delete by Rid
    public void DeleteSpecific(int rid)
    {
        int index = 0;
        for(Student sref : lobj)
        {
            if(sref.RID == rid)
            {
                lobj.remove(index);
                break;
            } 
            index++; 
        }
    }

    //Delete by name
    public void DeleteSpecific(String str)
    {
        int index = 0;
        for(Student sref : lobj)
        {
            if(str.equals(sref.Name))
            {
                lobj.remove(index);
                break;
            } 
            index++; 
        }
    }

    //for finding Maximum salary
    public void AggregateMax()
    {
        int iMax = 0;
        Student temp = null;

        for(Student sref : lobj)
        {
            if(sref.Salary > iMax)
            {
                iMax = sref.Salary;
                temp = sref;     
            }
        }
        System.out.println("Information of student having maximum salary:  ");
        temp.DisplayData();
    }

    //for finding Minimum salary
    public void AggregateMin()
    {
        int iMin = (lobj.getFirst()).Salary;
        Student temp = lobj.getFirst();

        for(Student sref : lobj)
        {
            if(sref.Salary < iMin)
            {
                iMin = sref.Salary;
                temp = sref;
            }
        }
        System.out.println("Information of student having minimum salary:  ");
        temp.DisplayData();
    }

    public void AggregateSum()
    {
        long iSum = 0;

        for(Student sref : lobj)
        {
            iSum = iSum + sref.Salary;
        }
        System.out.println("Smmation of salaries:  "+iSum);
    }

    //For summation of salary
    public void AggregateAverage()
    {
        int iAvg = 0;
        int iSum = 0;

        for(Student sref : lobj)
        {
            iSum = iSum + sref.Salary;
        }
        System.out.println("Average salary is:  "+iSum/(lobj.size()));
    }

    //For counting data
    public void AggregateCount()
    {
        System.out.println("Count is:  "+(lobj.size()));
    }
    
}

//Main class
class CustomisedDBMS
{
    public static void main(String args[])
    {
        DBMS dobj = new DBMS();

        dobj.StartDBMS();

        /* 
        ............Statical data...........
        dobj.InsertData("Piyush", 3000);
        dobj.InsertData("Preeti", 2000);
        dobj.InsertData("Pranav", 8000);
        dobj.InsertData("Jagtap", 4000);
        dobj.InsertData("Thange", 3000);
        dobj.InsertData("Khairnar", 1000);



        // dobj.DisplayAll();
        //dobj.DisplaySpecific(2);
        //dobj.DisplaySpecific("Pranav");

        // dobj.DeleteSpecific(2);
        // dobj.DeleteSpecific("Jatap");
        // dobj.DisplayAll();

        dobj.AggregateMax();
        dobj.AggregateMin();
        dobj.AggregateSum();
        dobj.AggregateAverage();
        dobj.AggregateCount();
        */
    }
}
