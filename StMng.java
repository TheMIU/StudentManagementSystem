import java.util.Scanner;
import java.util.Arrays;

class StMng{
    static String[][] student=new String [0][2];
    static int[][] marks = new int[0][2];
    static int[][] all = new int[0][5];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[]args){
        int menu;
        while(true){
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("|\t\t\t WELCOME TO GDSE MARKS MANAGEMENT SYSTEM \t\t\t|");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println();
            System.out.printf("%-40s %1s %n","[1] Add New Student","[2] Add New Student With Marks");
            System.out.printf("%-40s %1s %n","[3] Add Marks","[4] Update Student Details");
            System.out.printf("%-40s %1s %n","[5] Update Marks","[6] Delete Student");
            System.out.printf("%-40s %1s %n","[7] Print Student Details","[8] Print Student Ranks");
            System.out.printf("%-40s %1s %n","[9] Best in Programming Fundamentals ","[10] Best in Database Management System");
            System.out.println();
            System.out.print("Enter an option to continue > ");

            menu = sc.nextInt();
            switch (menu){
                case 1 : cls(); addStudent(); break;
                case 2 : cls(); addStudentWithMarks(); break;
                case 3 : cls(); addMarksOnly(); break;
                case 4 : cls(); updateStudent(); break;
                case 5 : cls(); updateMarks(); break;
                case 6 : cls(); deleteStudent(); break;
                case 7 : cls(); printStudent(); break;
                case 8 : cls(); printRanks(); break;
                case 9 : cls(); bestPRF(); break;
                case 10 : cls();bestDBMS(); break;
                default : {	cls();
                    System.out.println("Error input ! Enter one of the following options");
                } break;
            }
        }
    }

    public static void addStudent(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t ADD NEW STUDENT \t\t\t\t\t|");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();

        boolean duplicate = true;
        while(duplicate == true){
            System.out.print("Enter Student ID \t: ");
            String id = sc.next();
            duplicate = checkDuplicate(id);// check Student ID duplicated or not

            if(duplicate==true){
                System.out.println("The Student ID Already Exists ! \n");
            }
            else if(duplicate==false){
                System.out.print("Enter Student Name \t: ");
                String name = sc.next();
                // assign id and name to extended array ,,, and extend marks array
                extend_assign_studentArray(id, name);
                extend_marksArray();
                System.out.println();
                System.out.print("Student has been added successfully.");

                int ans = 0;
                while(ans==0){
                    System.out.print("Do you want to add a new student ? (Y/N) : ");
                    ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
                    switch(ans){
                        case 1 : cls(); addStudent(); break;
                        case 2 : cls(); break;
                        case 0 : System.out.print("Error input ! "); break;
                    }
                }
            }
        }
    }

    public static void addStudentWithMarks(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t ADD NEW STUDENT WITH MARKS \t\t\t\t|");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();

        boolean duplicate = true;
        while(duplicate == true){
            System.out.print("Enter Student ID \t: ");
            String id = sc.next();
            duplicate = checkDuplicate(id);// check Student ID duplicated or not

            if(duplicate==true){
                System.out.println("The Student ID Already Exists ! \n");
            }
            else if(duplicate==false){
                System.out.print("Enter Student Name \t: ");
                String name = sc.next();
                // assign id and name to extended array ,,, and extend marks array
                extend_assign_studentArray(id, name);
                extend_marksArray();
                System.out.println();

                addMarks(id);
                System.out.print("Student and Marks has been added successfully.");

                int ans = 0;
                while(ans==0){
                    System.out.print("Do you want to add a new student ? (Y/N) : ");
                    ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
                    switch(ans){
                        case 1 : cls(); addStudentWithMarks(); break;
                        case 2 : cls(); break;
                        case 0 : System.out.print("Error input ! "); break;
                    }
                }
            }
        }
    }

    public static void addMarksOnly(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\t ADD MARKS \t\t\t\t\t|");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();

        System.out.print("Enter Student ID \t: ");
        String id = sc.next();
        boolean duplicate = checkDuplicate(id);// check Student ID duplicated or not

        if(duplicate==false){
            System.out.println("Invalid ID !\n");

            int ans = 0;
            while(ans==0){
                System.out.print("Do you want to Search again ? (Y/N) : ");
                ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
                switch(ans){
                    case 1 : cls(); addMarksOnly(); break;
                    case 2 : cls(); break;
                    case 0 : System.out.print("Error input ! "); break;
                }
            }
        }

        else if(duplicate==true){
            System.out.println("Student name \t\t: "+readStudentName(id));
            boolean added = checkMarksAdded(id);
            if(added==true){
                System.out.println("Marks already added");
            }
            else if(added==false){
                addMarks(id);
                System.out.println("Marks successfully added. ");
            }

            int ans = 0;
            while(ans==0){
                System.out.print("Do you want to add a another marks ? (Y/N) : ");
                ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
                switch(ans){
                    case 1 : cls(); addMarksOnly(); break;
                    case 2 : cls(); break;
                    case 0 : System.out.print("Error input ! "); break;
                }
            }
        }
    }

    public static void updateStudent(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t UPDATE STUDENT DETAILS \t\t\t\t|");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();

        System.out.print("Enter Student ID \t: ");
        String id = sc.next();
        boolean duplicate = checkDuplicate(id);// check Student ID duplicated or not

        if(duplicate==false){
            System.out.println("Invalid ID !\n");

            int ans = 0;
            while(ans==0){
                System.out.print("Do you want to Search again ? (Y/N) : ");
                ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
                switch(ans){
                    case 1 : cls(); updateStudent(); break;
                    case 2 : cls(); break;
                    case 0 : System.out.print("Error input ! "); break;
                }
            }
        }

        else if(duplicate==true){
            System.out.println("Student name \t\t: "+readStudentName(id));
            System.out.println();
            System.out.print("Enter new name : ");
            String newName= sc.next();
            // update name on student array
            for(int i=0; i<student.length; i++){
                if(id.equals(student[i][0])){
                    student[i][1]=newName;
                }
            }
            System.out.println(Arrays.deepToString(student));
            System.out.println("Successfully updated ! ");

            int ans = 0;
            while(ans==0){
                System.out.print("Do you want to update another Student ? (Y/N) : ");
                ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
                switch(ans){
                    case 1 : cls(); updateStudent(); break;
                    case 2 : cls(); break;
                    case 0 : System.out.print("Error input ! "); break;
                }
            }
        }
    }

    public static void updateMarks(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\t UPDATE MARKS \t\t\t\t\t|");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();

        System.out.print("Enter Student ID \t: ");
        String id = sc.next();
        boolean duplicate = checkDuplicate(id);// check Student ID duplicated or not

        if(duplicate==false){
            System.out.println("Invalid ID !\n");

            int ans = 0;
            while(ans==0){
                System.out.print("Do you want to Search again ? (Y/N) : ");
                ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
                switch(ans){
                    case 1 : cls(); updateMarks(); break;
                    case 2 : cls(); break;
                    case 0 : System.out.print("Error input ! "); break;
                }
            }
        }

        else if(duplicate==true){
            System.out.println("Student name \t\t: "+readStudentName(id));
            System.out.println();

            boolean added = checkMarksAdded(id);
            if(added == true){
                int index = getIndexString(id);
                System.out.println("Programming Fundamentals Marks \t\t: "+marks[index][0]);
                System.out.println("Database Management System  Marks \t: "+marks[index][1]);
                System.out.println();
                System.out.println("Enter new marks");
                addMarks(id);
                System.out.println("Successfully updated !");
            }

            else if(added == false){
                System.out.println("Marks not yet added ! ");
            }

            int ans = 0;
            while(ans==0){
                System.out.print("Do you want to update another marks ? (Y/N) : ");
                ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
                switch(ans){
                    case 1 : cls(); updateMarks(); break;
                    case 2 : cls(); break;
                    case 0 : System.out.print("Error input ! "); break;
                }
            }
        }
    }

    public static void deleteStudent(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t DELETE STUDENT \t\t\t\t\t|");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();

        System.out.print("Enter Student ID \t: ");
        String id = sc.next();
        boolean duplicate = checkDuplicate(id);// check Student ID duplicated or not

        if(duplicate==false){
            System.out.println("Invalid ID !\n");

            int ans = 0;
            while(ans==0){
                System.out.print("Do you want to Search again ? (Y/N) : ");
                ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
                switch(ans){
                    case 1 : cls(); deleteStudent(); break;
                    case 2 : cls(); break;
                    case 0 : System.out.print("Error input ! "); break;
                }
            }
        }

        else if(duplicate==true){
            System.out.println("Student name \t\t: "+readStudentName(id));
            System.out.println();

            int index = getIndexString(id); // get the index of delete id

            marks[index][0]=marks[marks.length-1][0];  // last values of array copy to delete id
            marks[index][1]=marks[marks.length-1][1];

            int [][] tempMarks = new int [marks.length-1][2];

            for(int i=0; i<tempMarks.length; i++){				// copy to temp array , without last value
                for(int j=0; j<tempMarks[i].length; j++){
                    tempMarks[i][0]=marks[i][0];
                    tempMarks[i][1]=marks[i][1];
                }
            }
            marks = tempMarks;

            student[index][0] = student[student.length-1][0]; 	// last values of array copy to delete id
            student[index][1] = student[student.length-1][1];

            String tempSt[][] = new String [student.length-1][2];

            for(int i=0; i<tempSt.length; i++){
                for(int j=0; j<tempSt[i].length; j++){ 			// copy to temp array , without last value
                    tempSt[i][0]=student[i][0];
                    tempSt[i][1]=student[i][1];
                }
            }
            student = tempSt;
            System.out.print("Successfully deleted. ");

            int ans = 0;
            while(ans==0){
                System.out.print("Do you want to update another marks ? (Y/N) : ");
                ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
                switch(ans){
                    case 1 : cls(); deleteStudent(); break;
                    case 2 : cls(); break;
                    case 0 : System.out.print("Error input ! "); break;
                }
            }
        }
    }

    public static void printStudent(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t PRINT STUDENT DETAILS \t\t\t\t\t|");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();

        System.out.print("Enter Student ID \t: ");
        String id = sc.next();
        boolean duplicate = checkDuplicate(id);// check Student ID duplicated or not

        if(duplicate==false){
            System.out.println("Invalid ID !\n");

            int ans = 0;
            while(ans==0){
                System.out.print("Do you want to Search again ? (Y/N) : ");
                ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
                switch(ans){
                    case 1 : cls(); printStudent(); break;
                    case 2 : cls(); break;
                    case 0 : System.out.print("Error input ! "); break;
                }
            }
        }

        else if(duplicate==true){
            System.out.println("Student name \t\t: "+givenIndexSimilerValue(id,student));
            System.out.println();

            boolean added = checkMarksAdded(id);
            if(added == true){
                createAllArrayDesc(3); // 1-prf 2-dbms 3-total
                int idIndex = getIndexString(id);
                int indAll= 0;
                for(int i=0; i<all.length; i++){
                    if(all[i][0]==idIndex){
                        indAll = i;
                        break;
                    }
                }
                System.out.println("+---------------------------------------+--------------+");
                System.out.printf("%s %11d %s %n","|Programming Fundamentals Marks \t: ",all[indAll][1],"|");
                System.out.printf("%s %11d %s %n","|Database Management System  Marks \t: ",all[indAll][2],"|");

                int tot = all[indAll][3];

                System.out.printf("%s %11d %s %n","|Total Marks \t\t\t\t: ",tot,"|");
                System.out.printf("%s %11.2f %s %n","|Avg. Marks \t\t\t\t: ",(double)(tot)/2,"|");

                int r = all[indAll][4];
                //givenIndexSimilerValue(idIndex,rank);  // 1
                String rankName = getRankName(r);  // (first)

                System.out.printf("%s %2d %-8s %s %n","|Rank \t\t\t\t\t: ",r,rankName,"|");
                System.out.println("+---------------------------------------+--------------+");
                System.out.println();
            }

            else if(added == false){
                System.out.println("Marks not yet added ! ");
            }

            int ans = 0;
            while(ans==0){
                System.out.print("Do you want to Search another student ? (Y/N) : ");
                ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
                switch(ans){
                    case 1 : cls(); printStudent(); break;
                    case 2 : cls(); break;
                    case 0 : System.out.print("Error input ! "); break;
                }
            }
        }
    }

    public static void printRanks(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t PRINT STUDENTS' RANKS \t\t\t\t\t|");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();
        // check marks added at least 1
        boolean added = checkMarksAdded();
        if(added == true){
            System.out.println("+----------+----------+--------------------+------------+-----------+");
            System.out.printf("%-10s %-10s %-20s %-10s %-10s %s %n","|Rank","|ID","|Name","|Total marks","|Avg. marks","|");
            System.out.println("+----------+----------+--------------------+------------+-----------+");
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            createAllArrayDesc(3); // 1-prf 2-dbms 3-total
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            for (int[] ints : all) {
                if (ints[3] != -2) {
                    int r = ints[4]; //<<<<<<<<<<<<
                    int indexId = ints[0];
                    String id = student[indexId][0];
                    String name = student[indexId][1];
                    int tot = ints[3];
                    double avg = (double) (tot) / 2;
                    System.out.printf("%-10s %-10s %-20s %s %10d %s %9.2f %s %n", "|" + r, "|" + id, "|" + name, "|", tot, "|", avg, "|");
                }
            }
            System.out.println("+----------+----------+--------------------+------------+-----------+");
        }else if(added == false){
            System.out.println("Marks not yet added ! ");
        }

        int ans = 0;
        while(ans==0){
            System.out.println();
            System.out.print("Do you want to go back to main menu ? (Y/N) : ");
            ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
            switch(ans){
                case 1 : cls(); break;
                case 2 : cls(); printRanks(); break;
                case 0 : System.out.print("Error input ! "); break;
            }
        }
    }

    public static void bestPRF(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t BEST IN PROGRAMMING FUNDAMENTALS \t\t\t\t|");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();
        // check marks added at least 1
        boolean added = checkMarksAdded();
        if(added == true){
            System.out.println("+----------+--------------------+------------+-----------+");
            System.out.printf("%-10s %-20s %-10s %-10s %s %n","|ID","|Name","|  PRF marks","|DBMS marks","|");
            System.out.println("+----------+--------------------+------------+-----------+");
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            createAllArrayDesc(1); // 1-prf 2-dbms 3-total
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            for(int i = 0; i<all.length; i++){
                if(all[i][3]!= -2){
                    int idIndex = all[i][0];
                    String id = student[idIndex][0];
                    String name = student[idIndex][1];
                    int prf = all[i][1];
                    int dbms = all[i][2];
                    System.out.printf("%-10s %-20s %s %10d %s %9d %s %n","|"+id,"|"+name,"|",prf,"|",dbms,"|");
                }
            }
            System.out.println("+----------+--------------------+------------+-----------+");
        }else if(added == false){
            System.out.println("Marks not yet added ! ");
        }

        int ans = 0;
        while(ans==0){
            System.out.println();
            System.out.print("Do you want to go back to main menu ? (Y/N) : ");
            ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
            switch(ans){
                case 1 : cls(); break;
                case 2 : cls(); bestPRF(); break;
                case 0 : System.out.print("Error input ! "); break;
            }
        }
    }

    public static void bestDBMS(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t BEST IN PROGRAMMING FUNDAMENTALS \t\t\t\t|");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();
        // check marks added at least 1
        boolean added = checkMarksAdded();
        if(added == true){
            System.out.println("+----------+--------------------+------------+-----------+");
            System.out.printf("%-10s %-20s %-10s %-10s %s %n","|ID","|Name","| DBMS marks","| PRF marks","|");
            System.out.println("+----------+--------------------+------------+-----------+");
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            createAllArrayDesc(2); // 1-prf 2-dbms 3-total
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            for(int i = 0; i<all.length; i++){
                if(all[i][3]!= -2){
                    int idIndex = all[i][0];
                    String id = student[idIndex][0];
                    String name = student[idIndex][1];
                    int prf = all[i][1];
                    int dbms = all[i][2];
                    System.out.printf("%-10s %-20s %s %10d %s %9d %s %n","|"+id,"|"+name,"|",dbms,"|",prf,"|");
                }
            }
            System.out.println("+----------+--------------------+------------+-----------+");
        }else if(added == false){
            System.out.println("Marks not yet added ! ");
        }

        int ans = 0;
        while(ans==0){
            System.out.println();
            System.out.print("Do you want to go back to main menu ? (Y/N) : ");
            ans = getUserAns(); // ans 1 == y  , ans 2 == n , ans 0 == other
            switch(ans){
                case 1 : cls(); break;
                case 2 : cls(); bestDBMS(); break;
                case 0 : System.out.print("Error input ! "); break;
            }
        }
    }
    //=================================================================================================================
    public static void createAllArrayDesc(int a){
        all = new int[student.length][5];

        for(int i=0; i<student.length; i++){
            all[i][0]=i;	// student ID index
            all[i][1]=marks[i][0];	// prf marks
            all[i][2]=marks[i][1];	// dbms marks
            all[i][3]=marks[i][0]+marks[i][1];	// total
            all[i][4]=0;	// rank
        }

        //System.out.println(Arrays.deepToString(all));
        
        for(int i=0; i<all.length-1; i++){
            for(int j=0; j<all.length-1; j++){
                if(all[j][a]<all[j+1][a]){   // According to columns a= 1-prf 2-dbms 3-total // Descending order
                    int temp1 = all[j][0];
                    int temp2 = all[j][1];
                    int temp3 = all[j][2];
                    int temp4 = all[j][3];
                    int temp5 = all[j][4];
                    all[j][0] = all[j+1][0];
                    all[j][1] = all[j+1][1];
                    all[j][2] = all[j+1][2];
                    all[j][3] = all[j+1][3];
                    all[j][4] = all[j+1][4];
                    all[j+1][0] = temp1;
                    all[j+1][1] = temp2;
                    all[j+1][2] = temp3;
                    all[j+1][3] = temp4;
                    all[j+1][4] = temp5;
                }
            }
        }

        //add ranks
        int r = 1;
        for(int i=0; i<all.length; i++){
            if(all[i][3]!=-2){
                all[i][4]= r++;
            }
        }
       // System.out.println(Arrays.deepToString(all));
    }

    public static String givenIndexSimilerValue(String index, String[][] arr){
        for(int i=0; i<arr.length; i++){
            if(index .equals(arr[i][0])){
                return arr[i][1];
            }
        }
        return "";
    }

    public static String getRankName(int rank){
        int trueLength = 0;
        for(int i=0;i<all.length; i++){
            if(all[i][3]!=-2){
                ++trueLength;
            }
        }
        if(rank==1){return " (first)";}
        else if(rank==2){return "(second)";}
        else if(rank==3){return " (third)";}
        else if(rank==trueLength){return " (last)";}
        else return "("+rank+" th)";
    }

    public static int getIndexString(String id){
        for(int i = 0; i<student.length; i++){
            if (id.equals(student[i][0])){
                return i;
            }
        }
        return 0;
    }

    public static boolean checkMarksAdded(){  // Check at least one mark is added
        if(marks.length>0){
            for(int i=0; i<marks.length; i++){
                if(marks[i][0]>0){
                    return true;
                }
            }
        }else{
            return false;
        }
        return false;
    }

    public static boolean checkMarksAdded(String id){	// on specific index
        int index = getIndexString(id);
        // check marks on selected index
        if(marks[index][0]==-1){
            return false;
        }else{
            return true;
        }
    }

    public static String readStudentName(String id){   // <<<<<<<<  remove this  givenIndexSimilerValue
        for(int i = 0; i<student.length; i++){
            for(int j=0; j<student[i].length; j++){
                if(id.equals(student[i][0])){
                    return student[i][1];
                }
            }
        }
        return "";
    }

    public static void addMarks(String id){
        int prf = 0;
        int dbms =0;

        boolean prfValid = false;
        while(prfValid == false){
            System.out.print("Programming Fundamentals Marks \t\t: ");
            prf = sc.nextInt();
            prfValid = checkMarksValidity(prf);
        }

        boolean dbmsValid = false;
        while(dbmsValid == false){
            System.out.print("Database Management System  Marks \t: ");
            dbms = sc.nextInt();
            dbmsValid = checkMarksValidity(dbms);
        }

        assign_marksArray(prf,dbms,id); // assign prf and dbms marks to array marks last index
        System.out.println();
    }

    public static boolean checkMarksValidity(int mark){
        if (mark>=0 && mark<=100){
            return true;
        }else{
            System.out.println("\nInvalid Marks ! Please Enter Correct Marks ");
        }
        return false;
    }

    public static int getUserAns(){
        Scanner sc = new Scanner(System.in);
        char ans = sc.next().charAt(0);

        if(ans == 'y' || ans == 'Y'){return 1;}
        else if (ans == 'n' || ans == 'N'){return 2;}
        else{return 0;}
    }

    public static void extend_assign_studentArray(String id, String name){
        // extend Array
        String[][] temp = new String[student.length+1][2];
        for(int i=0; i<student.length; i++){
            for(int j=0; j<student[i].length; j++){
                temp[i][j] = student[i][j];
            }
        }
        student = temp;

        // initialize Array
        student[student.length-1][0]=id;
        student[student.length-1][1]=name;
        System.out.println(Arrays.deepToString(student));
    }

    public static void extend_marksArray(){
        int[][] temp = new int[marks.length+1][2];
        for(int i=0; i<marks.length; i++){
            for(int j=0; j<marks[i].length; j++){
                temp[i][j] = marks[i][j];
            }
        }
        marks = temp;
        marks[marks.length-1][0]=-1;  // default value -1 (== marks not added)
        marks[marks.length-1][1]=-1;
    }

    public static void assign_marksArray(int prf, int dbms,String id){
        int index = getIndexString(id);
        marks[index][0]=prf;
        marks[index][1]=dbms;
        System.out.println(Arrays.deepToString(marks));
    }

    public static boolean checkDuplicate(String id){
        for(int i=0; i<student.length; i++){
            if(student[i][0].equals(id)){
                return true;
            }
        }
        return false;
    }

    // clear console
    public static void cls(){
        try{
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }catch(Exception E){
            System.out.println(E);
        }
    }
}
