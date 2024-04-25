import java.util.*;

public class Ayoma{

    private static Scanner input = new Scanner(System.in);
    private static String[] credentials = {"Ayoma", "ayo123"};
    private static String[][] information = new String[1][2];
    private static String[] itemCategory = new String[1];
    private static String[][] itemCatDetails = new String[1][6];


    private final static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.equals("Linux")) {
                System.out.print("\033\143");
            } else if (os.equals("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            //handle the exception
            System.err.println(e.getMessage());
        }
    }

    public static void lineAnimation(){
        System.out.print("+");
        for (int i = 0; i < 49; i++)
        {
            try{Thread.sleep(5);}catch(Exception e){}
            System.out.print("--");
        }
        //System.out.println("\b\b\b\b ");
        System.out.println("+");
    }

    public static void logOut(){

        loginPage();
        clearConsole();
        homePage();
    }

    public static void sortUnitPrice(){

        for (int i = 0; i < itemCatDetails.length - 1; i++) {
            for (int j = 0; j < itemCatDetails.length - 1; j++) {

                if(Double.parseDouble(itemCatDetails[j][3]) > Double.parseDouble(itemCatDetails[j+1][3])) {

                    String[] temp = new String[6];

                    for(int k = 0; k < temp.length; k++){

                        temp[k] = itemCatDetails[j][k];
                        itemCatDetails[j][k] = itemCatDetails[j+1][k];
                        itemCatDetails[j+1][k] = temp[k];
                    }
                }
            }
        }
    }

    public static void rankItems(){

        lineAnimation();
        System.out.printf("|%55s","RANKED UNIT PRICE");//+
        System.out.printf("%43s|\n"," ");//-
        lineAnimation();

        sortUnitPrice();

        System.out.printf("\n\n+------------------+-------------------+-------------------+-------------------+-------------------+-------------------+%n");
        System.out.printf("|        %s       |        %s       |        %s       |       %s       |        %s        |        %s        |%n","SID","CODE","DESC","PRICE","QTY","CAT");
        System.out.printf("+------------------+-------------------+-------------------+-------------------+-------------------+-------------------+%n");

        for (int i = 0; i < itemCatDetails.length; i++){
            System.out.printf("|  %10s      |  %10s       |  %10s       |   %10s      |%10s         |  %10s       |%n",itemCatDetails[i][0],itemCatDetails[i][1],itemCatDetails[i][2],Double.parseDouble(itemCatDetails[i][3]),itemCatDetails[i][4],itemCatDetails[i][5]);
        }
        System.out.printf("+------------------+-------------------+-------------------+-------------------+-------------------+-------------------+%n");

        System.out.print("\n\nDo you want to go stock manage page (Y/N) ?  :  ");
        char ch = input.next().charAt(0);

        if((ch == 'Y') || (ch == 'y')){
            clearConsole();
            stockManage();
        }else if((ch == 'N') || (ch == 'n')){
            clearConsole();
            homePage();
        }else{
            clearConsole();
            System.out.println("\nWrong input. Please try again!\n\n");
            homePage();
        }
    }

    public static void viewItems(){

        lineAnimation();
        System.out.printf("|%55s","VIEW ITEMS");//+
        System.out.printf("%43s|\n"," ");//-
        lineAnimation();

        for(int i = 0; i < itemCategory.length; i++){

            System.out.println("\n\n" + itemCategory[i]  + ": ");
            System.out.printf("+------------------+-------------------+-------------------+-------------------+-------------------+%n");
            System.out.printf("|       %s        |        %s       |       %s        |       %s       |        %s        |%n","SID","CODE","DESC","PRICE","QTY");
            System.out.printf("+------------------+-------------------+-------------------+-------------------+-------------------+%n");

            for (int j = 0; j < itemCatDetails.length; j++){
                if(itemCatDetails[j][5].equals(itemCategory[i])){
                    System.out.printf("|%11s       |        %-9s  |%11s        |  %10s       |%10s         |%n",itemCatDetails[j][0],itemCatDetails[j][1],itemCatDetails[j][2],Double.parseDouble(itemCatDetails[j][3]),itemCatDetails[j][4]);
                }
            }
            System.out.printf("+------------------+-------------------+-------------------+-------------------+-------------------+%n");
        }

        System.out.print("\n\nDo you want to go stock manage page (Y/N) ?  :  ");
        char ch = input.next().charAt(0);

        if((ch == 'Y') || (ch == 'y')){
            clearConsole();
            stockManage();
        }else if((ch == 'N') || (ch == 'n')){
            clearConsole();
            logOut();
        }else{
            clearConsole();
            System.out.println("\nWrong input. Please try again!\n\n");
            homePage();
        }
    }

    public static void getItems(){

        lineAnimation();
        System.out.printf("|%61s","SEARCH ITEMS SUPPLIER WISE");//+
        System.out.printf("%37s|\n"," ");//-
        lineAnimation();

        do{
            System.out.print("\n\nEnter Supplier ID	:  ");
            String supId = input.next();

            for(int i = 0; i < information.length; i++){

                if(supId.equals(information[i][0])){

                    System.out.println("Supplier Name		:  " + information[i][1]);
                    System.out.printf("\n+------------------+-------------------+-------------------+-------------------+-------------------+%n");
                    System.out.printf("|     %s    |    %s    |     %s    |     %s   |     %s      |%n","ITEM CODE","DESCRIPTION","UNIT PRICE","QTY ON HAND","CATEGORY");
                    System.out.printf("+------------------+-------------------+-------------------+-------------------+-------------------+%n");

                    for(int j = 0; j < itemCatDetails.length; j++){
                        if(supId.equals(itemCatDetails[j][0])){
                            System.out.printf("|%11s       |       %-9s   |%13s      |%11s        |%12s       |%n",itemCatDetails[j][1],itemCatDetails[j][2],Double.parseDouble(itemCatDetails[j][3]),itemCatDetails[j][4],itemCatDetails[j][5]);
                        }
                    }
                    System.out.printf("+------------------+-------------------+-------------------+-------------------+-------------------+%n");

                    System.out.print("\n\nSearch successfully! Do you want to search another item (Y/N) ? :  ");
                    char ch = input.next().charAt(0);

                    if((ch == 'Y') || (ch == 'y')){
                        clearConsole();
                        getItems();
                    }else if((ch == 'N') || (ch == 'n')){
                        clearConsole();
                        stockManage();
                    }else{
                        clearConsole();
                        System.out.println("\nWrong input. Please try again!\n\n");
                        homePage();
                    }
                }
            }
            System.out.println("Invalid Supplier ID. Please try again!\n");
        }while(true);
    }

    public static String[][] itemCatDetailsGrow(){

        String[][] temp = new String[itemCatDetails.length + 1][6];
        for(int i = 0; i < itemCatDetails.length; i++){
            for(int j = 0; j < itemCatDetails[i].length; j++){
                temp[i][j] = itemCatDetails[i][j];
            }
        }
        return temp;
    }

    public static void addItems(){

        lineAnimation();
        System.out.printf("|%53s","ADD ITEM");//+
        System.out.printf("%45s|\n"," ");//-
        lineAnimation();

        for(int i = 0; i < itemCategory.length; i++){

            if(itemCategory[i] == null){

                System.out.println("\nOOPS! It seems that you don't have any item categories in the system." );

                System.out.print("\nDo you want to add a new item category (Y/N) ?  :  " );
                char ch = input.next().charAt(0);

                if((ch == 'Y') || (ch == 'y')){
                    clearConsole();
                    addItemCategory();
                }else if((ch == 'N') || (ch == 'n')){
                    clearConsole();
                    stockManage();
                }else{
                    clearConsole();
                    System.out.println("\nWrong input. Please try again!\n\n");
                    homePage();
                }
            }

            for(int j = 0; j < information.length; j++){

                if(information[j][0] == null){

                    System.out.println("\nOOPS! It seems that you don't have any suppliers in the system." );

                    System.out.print("\nDo you want to add a new supplier (Y/N) ?  :  " );
                    char ch = input.next().charAt(0);

                    if((ch == 'Y') || (ch == 'y')){
                        clearConsole();
                        addSupplier();
                    }else if((ch == 'N') || (ch == 'n')){
                        clearConsole();
                        supplierManage();
                    }else{
                        clearConsole();
                        System.out.println("\nWrong input. Please try again!\n\n");
                        homePage();
                    }
                }

                L1:
                do{
                    System.out.print("\nItem Code	:  " );
                    String code = input.next();

                    for(int k = 0; k < itemCatDetails.length; k++){

                        if(code.equals(itemCatDetails[k][1])){
                            System.out.println("Already added. try another item code!");
                            continue L1;
                        }
                    }
                    itemCatDetails[itemCatDetails.length - 1][1] = code;

                    System.out.println("\n\nSuppliers List  :" );
                    System.out.printf("+----------------------+----------------------+----------------------+%n");
                    System.out.printf("| %20s | %20s | %20s |%n","SUPPLIER NUMBER  ","SUPPLIER ID    ","SUPPLIER NAME   ");
                    System.out.printf("+----------------------+----------------------+----------------------+%n");

                    for (int x = 0; x < information.length; x++){
                        System.out.printf("|          %-11s |         %-12s |         %-12s |%n",(x+1),information[x][0],information[x][1]);
                    }

                    System.out.printf("+----------------------+----------------------+----------------------+%n");

                    System.out.print("\nEnter the supplier number  >  ");
                    int supNumber = input.nextInt();
                    itemCatDetails[itemCatDetails.length - 1][0] = information[supNumber - 1][0];

                    System.out.println("\n\nItem Categories :");
                    System.out.printf("+----------------------+----------------------+%n");
                    System.out.printf("| %20s | %20s |%n","CATEGORY NUMBER  ","CATEGORY NAME   ");
                    System.out.printf("+----------------------+----------------------+%n");

                    for (int y = 0; y < itemCategory.length; y++){
                        System.out.printf("|          %-11s |         %-12s |%n",(y+1),itemCategory[y]);
                    }

                    System.out.printf("+----------------------+----------------------+%n");

                    System.out.print("\nEnter the category number  >  ");
                    int catNumber = input.nextInt();
                    itemCatDetails[itemCatDetails.length - 1][5] = itemCategory[catNumber - 1];

                    System.out.print("\n\nDescription       :  ");
                    String description = input.next();
                    itemCatDetails[itemCatDetails.length - 1][2] = description;

                    System.out.print("Unit Price        :  ");
                    String price = input.next();
                    itemCatDetails[itemCatDetails.length - 1][3] = price;

                    System.out.print("Quantity On Hand  :  ");
                    String qty = input.next();
                    itemCatDetails[itemCatDetails.length - 1][4] = qty;

                    System.out.print("\n\nAdded successfully! Do you want to add another Item (Y/N) ?  :  ");
                    char ch = input.next().charAt(0);

                    if((ch == 'Y') || (ch == 'y')){
                        itemCatDetails = itemCatDetailsGrow();
                        clearConsole();

                        lineAnimation();
                        System.out.printf("|%49s","ADD ITEM");//+
                        System.out.printf("%49s|\n"," ");//-
                        lineAnimation();

                        continue L1;

                    }else if((ch == 'N') || (ch == 'n')){
                        clearConsole();
                        stockManage();
                    }else{
                        clearConsole();
                        System.out.println("\nWrong input. Please try again!\n\n");
                        homePage();
                    }
                }while(true);
            }
        }
    }

    public static void updateItemCategory(){

        lineAnimation();
        System.out.printf("|%59s","UPDATE ITEM CATEGORY");//+
        System.out.printf("%39s|\n"," ");//-
        lineAnimation();

        do{
            System.out.print("\nEnter the item category wanted to update  :  ");
            String category = input.next();

            for(int i = 0; i < itemCategory.length; i++){

                if(category.equals(itemCategory[i])){

                    System.out.print("\nEnter the new item category  :  ");
                    String newCategory = input.next();

                    itemCategory[i] = newCategory;

                    System.out.print("\n\nUpdated successfully! Do you want to update another category (Y/N) ?  :  ");
                    char ch = input.next().charAt(0);

                    if((ch == 'Y') || (ch == 'y')){
                        clearConsole();
                        updateItemCategory();
                    }else if((ch == 'N') || (ch == 'n')){
                        clearConsole();
                        stockManage();
                    }else{
                        clearConsole();
                        System.out.println("\nWrong input. Please try again!\n\n");
                        homePage();
                    }
                }
            }
            System.out.println("Can't find category. try again!");
        }while(true);
    }

    public static String[] itemCategoryRemove(int index){

        String[] temp = new String[itemCategory.length - 1];
        for(int i = 0, j = 0; i < itemCategory.length; i++){
            if(i == index){
                continue;
            }
            temp[j] = itemCategory[i];
            j++;
        }
        return temp;
    }

    public static void deleteItemCategory(){

        lineAnimation();
        System.out.printf("|%59s","DELETE ITEM CATEGORY");//+
        System.out.printf("%39s|\n"," ");//-
        lineAnimation();

        do{
            System.out.print("\nEnter the item category wanted to delete  :  ");
            String category = input.next();

            for(int i = 0; i < itemCategory.length; i++){

                if(category.equals(itemCategory[i])){
                    itemCategory = itemCategoryRemove(i);

                    System.out.print("\n\nDeleted successfully! Do you want to delete another category (Y/N) ?  :  ");
                    char ch = input.next().charAt(0);

                    if((ch == 'Y') || (ch == 'y')){
                        clearConsole();
                        deleteItemCategory();
                    }else if((ch == 'N') || (ch == 'n')){
                        clearConsole();
                        stockManage();
                    }else{
                        clearConsole();
                        System.out.println("\nWrong input. Please try again!\n\n");
                        homePage();
                    }
                }
            }
            System.out.println("Can't find category. try again!");
        }while(true);
    }

    public static String[] itemCategoryGrow(){

        String[] temp = new String[itemCategory.length + 1];
        for(int i = 0; i < itemCategory.length; i++){
            temp[i] = itemCategory[i];
        }
        return temp;
    }

    public static void addItemCategory(){

        lineAnimation();
        System.out.printf("|%57s","ADD ITEM CATEGORY");//+
        System.out.printf("%41s|\n"," ");//-
        lineAnimation();

        L1:
        do{
            System.out.print("\nEnter the new item category  :  ");
            String category = input.next();

            for(int i = 0; i < itemCategory.length; i++){

                if(category.equals(itemCategory[i])){
                    System.out.println("Already added. try another item category!\n");
                    continue L1;
                }
            }
            itemCategory[itemCategory.length - 1] = category;

            System.out.print("\n\nAdded successfully! Do you want to add another category (Y/N) ?  :  ");
            char ch = input.next().charAt(0);

            if((ch == 'Y') || (ch == 'y')){
                clearConsole();
                itemCategory = itemCategoryGrow();
                addItemCategory();
            }else if((ch == 'N') || (ch == 'n')){
                clearConsole();
                stockManage();
            }else{
                clearConsole();
                System.out.println("\nWrong input. Please try again!\n\n");
                homePage();
            }
        }while(true);
    }

    public static void manageItemsChoices(int option){

        if(option == 1){
            clearConsole();
            addItemCategory();
        }else if(option == 2){
            clearConsole();
            deleteItemCategory();
        }else if(option == 3){
            clearConsole();
            updateItemCategory();
        }else if(option == 4){
            clearConsole();
            stockManage();
        }else{
            clearConsole();
            System.out.println("\nInvalid option number. Please try again!\n\n");
            loginPage();
        }
    }

    public static void manageItems(){

        lineAnimation();
        System.out.printf("|%58s","MANAGE ITEM CATEGORY");//+
        System.out.printf("%40s|\n"," ");//-
        lineAnimation();

        System.out.println("\n[1]  Add New Item Category\t\t[2]  Delete Item Category");
        System.out.println("[3]  Update Item Category\t\t[4]  Stock Management");

        System.out.print("\n\nEnter an option to continue  >  ");
        int option = input.nextInt();
        manageItemsChoices(option);
    }

    public static void stockManageChoices(int option){

        if(option == 1){
            clearConsole();
            manageItems();
        }else if(option == 2){
            clearConsole();
            addItems();
        }else if(option == 3){
            clearConsole();
            getItems();
        }else if(option == 4){
            clearConsole();
            viewItems();
        }else if(option == 5){
            clearConsole();
            rankItems();
        }else if(option == 6){
            clearConsole();
            homePage();
        }else{
            clearConsole();
            System.out.println("\nInvalid option number. Please try again!\n\n");
            loginPage();
        }
    }

    public static void stockManage(){

        lineAnimation();
        System.out.printf("|%57s","STOCK MANAGEMENT");//+
        System.out.printf("%41s|\n"," ");//-
        lineAnimation();

        System.out.println("\n[1]  Manage Item Categories\t\t\t[2]  Add Item");
        System.out.println("[3]  Get Items Supplier Wise\t\t\t[4]  View Items");
        System.out.println("[5]  Rank Items Per Unit Price\t\t\t[6]  Home Page");

        System.out.print("\n\nEnter an option to continue  >  ");
        int option = input.nextInt();
        stockManageChoices(option);
    }

    public static void searchSupplier(){

        lineAnimation();
        System.out.printf("|%56s","SEARCH SUPPLIER");//+
        System.out.printf("%42s|\n"," ");//-
        lineAnimation();

        do{
            System.out.print("\nSupplier ID	: ");
            String id = input.next();

            for(int i = 0; i < information.length; i++){

                if(id.equals(information[i][0])){

                    System.out.println("Supplier Name	: " + information[i][1]);
                    System.out.print("\n\nSearched successfully! Do you want to search another supplier (Y/N) ?  :  ");
                    char ch = input.next().charAt(0);

                    if((ch == 'Y') || (ch == 'y')){
                        clearConsole();
                        searchSupplier();
                    }else if((ch == 'N') || (ch == 'n')){
                        clearConsole();
                        supplierManage();
                    }else{
                        clearConsole();
                        System.out.println("\nWrong input. Please try again!\n\n");
                        homePage();
                    }
                }
            }
            System.out.println("Can't find supplier id. try again!");
        }while(true);
    }

    public static void viewSupplier(){

        lineAnimation();
        System.out.printf("|%55s","VIEW SUPPLIERS");//+
        System.out.printf("%43s|\n"," ");//-
        lineAnimation();

        int delay = 50;
        try {
            Thread.sleep(delay);
            System.out.printf("\n+-----------------------+-----------------------+%n");
            Thread.sleep(delay);
            System.out.printf("| %-21s | %-21s |%n","     SUPPLIER ID","    SUPPLIER NAME");
            Thread.sleep(delay);
            System.out.printf("+-----------------------+-----------------------+%n");
            for (int i = 0,j=0; i <information.length; i++){
                Thread.sleep(delay);
                System.out.printf("|         %-13s |         %-13s |%n",information[i][j],information[i][j+1]);
            }
            Thread.sleep(delay);
            System.out.printf("+-----------------------+-----------------------+%n");
        }catch(InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("\n\nDo you want to go supplier manage page (Y/N) ?  :  ");
        char ch = input.next().charAt(0);

        if((ch == 'Y') || (ch == 'y')){
            clearConsole();
            supplierManage();
        }else if((ch == 'N') || (ch == 'n')){
            clearConsole();
            viewSupplier();
        }else{
            clearConsole();
            System.out.println("\nWrong input. Please try again!\n\n");
            homePage();
        }
    }

    public static String[][] informationRemove(int index){

        String[][] temp = new String[information.length - 1][2];
        for(int i = 0, j = 0; i < information.length; i++){
            if(i == index){
                continue;
            }
            temp[j][0] = information[i][0];
            temp[j][1] = information[i][1];
            j++;
        }
        return temp;
    }

    public static void deleteSupplier(){

        lineAnimation();
        System.out.printf("|%57s","DELETE SUPPLIER");//+
        System.out.printf("%41s|\n"," ");//-
        lineAnimation();

        do{
            System.out.print("\nSupplier ID	:  ");
            String id = input.next();

            for(int i = 0; i < information.length; i++){

                if(id.equals(information[i][0])){
                    information = informationRemove(i);

                    System.out.print("\n\nDeleted successfully! Do you want to delete another supplier (Y/N) ?  :  ");
                    char ch = input.next().charAt(0);

                    if((ch == 'Y') || (ch == 'y')){
                        clearConsole();
                        deleteSupplier();
                    }else if((ch == 'N') || (ch == 'n')){
                        clearConsole();
                        supplierManage();
                    }else{
                        clearConsole();
                        System.out.println("\nWrong input. Please try again!\n\n");
                        homePage();
                    }
                }
            }
            System.out.println("Can't find supplier id. try again!");
        }while(true);
    }

    public static void updateSupplier(){

        lineAnimation();
        System.out.printf("|%57s","UPDATE SUPPLIER");//+
        System.out.printf("%41s|\n"," ");//-
        lineAnimation();

        do{
            System.out.print("\nSupplier ID	:  ");
            String id = input.next();

            for(int i = 0; i < information.length; i++){

                if(id.equals(information[i][0])){
                    System.out.println("Supplier Name	:  " + information[i][1]);

                    System.out.print("\nEnter the new supplier name : ");
                    information[i][1] = input.next();

                    System.out.print("\n\nUpdated successfully! Do you want to update another supplier (Y/N) ?  :  ");
                    char ch = input.next().charAt(0);

                    if((ch == 'Y') || (ch == 'y')){
                        clearConsole();
                        updateSupplier();
                    }else if((ch == 'N') || (ch == 'n')){
                        clearConsole();
                        supplierManage();
                    }else{
                        clearConsole();
                        System.out.println("\nWrong input. Please try again!\n\n");
                        homePage();
                    }
                }
            }
            System.out.println("Can't find supplier id. try again!");
        }while(true);
    }

    public static String[][] informationGrow(){

        String[][] temp = new String[information.length + 1][2];
        for(int i = 0; i < information.length ; i++){
            for(int j = 0; j < information[i].length; j++){
                temp[i][j] = information[i][j];
            }
        }
        return temp;
    }

    public static void addSupplier(){

        lineAnimation();
        System.out.printf("|%52s","ADD SUPPLIER");//+
        System.out.printf("%46s|\n"," ");//-
        lineAnimation();

        L1:
        do{
            System.out.print("\nSupplier ID	:  ");
            String id = input.next();

            for(int i = 0; i < information.length; i++){
                if(id.equals(information[i][0])){
                    System.out.println("Already exists. try another supplier id!");
                    continue L1;
                }
            }
            information[information.length - 1][0] = id;

            System.out.print("Supplier Name	:  ");
            information[information.length - 1][1] = input.next();

            System.out.print("\n\nAdded successfully! Do you want to add another supplier (Y/N) ?  :  ");
            char add = input.next().charAt(0);

            if((add == 'Y') || (add == 'y')){
                information = informationGrow();
                clearConsole();
                addSupplier();
            }else if((add == 'N') || (add == 'n')){
                clearConsole();
                supplierManage();
            }else{
                clearConsole();
                System.out.println("\nWrong input. Please try again!\n\n");
                homePage();
            }
        }while(true);
    }

    public static void supplierManageChoices(int option){

        if(option == 1){
            clearConsole();
            addSupplier();
        }else if(option == 2){
            clearConsole();
            updateSupplier();
        }else if(option == 3){
            clearConsole();
            deleteSupplier();
        }else if(option == 4){
            clearConsole();
            viewSupplier();
        }else if(option == 5){
            clearConsole();
            searchSupplier();
        }else if(option == 6){
            clearConsole();
            homePage();
        }else{
            clearConsole();
            System.out.println("\nInvalid option number. Please try again!\n\n");
            loginPage();
        }
    }

    public static void supplierManage(){

        lineAnimation();
        System.out.printf("|%54s","SUPPLIER MANAGE");//+
        System.out.printf("%44s|\n"," ");//-
        lineAnimation();

        System.out.println("\n[1]  Add Supplier \t\t[2]  Update Supplier");
        System.out.println("[3]  Delete Supplier \t\t[4]  View Supplier");
        System.out.println("[5]  Search Supplier \t\t[6]  Home Page");

        System.out.print("\n\nEnter an option to continue  >  ");
        int option = input.nextInt();
        supplierManageChoices(option);
    }

    public static void credentialManage(){

        lineAnimation();
        System.out.printf("|%53s","CREDENTIAL MANAGE");//+
        System.out.printf("%45s|\n"," ");//-
        lineAnimation();

        L1:
        do{
            System.out.print("\n\nPlease enter the user name to verify it's you :  ");
            String userName = input.next();

            boolean isMatched = checkUserNameValidity(userName);

            if(isMatched == false){
                System.out.println("invalid user name. try again!");
                continue;
            }else{
                System.out.println("\n\nHey " + userName);

                L2:
                do{
                    System.out.print("\n\nEnter your current password   :  ");
                    String pw = input.next();

                    boolean isCorrected = checkPassword(pw);

                    if(isCorrected == false){
                        System.out.println("incorrect password. try again!");
                        continue L2;
                    }else if(isCorrected == true){
                        System.out.print("\nEnter your new password  :  ");
                        String new_pw = input.next();

                        credentials[1] = new_pw;

                        System.out.print("\n\nPassword changed successfully! Do you want to go home page (Y/N) ?  :  ");
                        char ch = input.next().charAt(0);

                        if((ch == 'Y') || (ch == 'y')){
                            clearConsole();
                            homePage();
                        }else if((ch == 'N') || (ch == 'n')){
                            clearConsole();
                            credentialManage();
                        }else{
                            clearConsole();
                            System.out.println("\nWrong input. Please try again!\n\n");
                            homePage();
                        }
                        break L1;
                    }
                }while(true);
            }
        }while(true);
    }

    public static void homePageChoices(int option){

        if(option == 1){
            clearConsole();
            credentialManage();
        }else if(option == 2){
            clearConsole();
            supplierManage();
        }else if(option == 3){
            clearConsole();
            stockManage();
        }else if(option == 4){
            clearConsole();
            logOut();
        }else if(option == 5){
            clearConsole();
            System.out.println("\n====================  Good Bye!!!  ====================\n");
            System.exit(0);
        }else{
            clearConsole();
            System.out.println("\nInvalid option number. Please try again!\n\n");
            loginPage();
        }
    }

    public static void homePage(){

        lineAnimation();
        System.out.printf("|%67s","WELCOME TO IJSE STOCK MANAGEMENT SYSTEM");//+
        System.out.printf("%31s|\n"," ");//-
        lineAnimation();

        System.out.println("\n[1]  Change the Credentials \t\t[2]  Supplier Manage");
        System.out.println("[3]  Stock Manage \t\t\t[4]  Log out");
        System.out.println("[5]  Exit the system");

        System.out.print("\n\nEnter an option to continue  >  ");
        int option = input.nextInt();
        homePageChoices(option);
    }

    public static boolean checkPassword(String pw){

        return credentials[1].equals(pw) ? true : false;

    }

    public static boolean checkUserNameValidity(String userName){

        return credentials[0].equals(userName) ? true : false;

    }

    public static void loginPage(){

        lineAnimation();
        System.out.printf("|%55s","LOGIN PAGE");//+
        System.out.printf("%43s|\n"," ");//-
        lineAnimation();

        do{
            System.out.print("\nUser Name  :  ");
            String userName = input.next();

            boolean isMatched = checkUserNameValidity(userName);

            if(isMatched == false){
                System.out.println("user name is invalid. please try again!");
                continue;
            }else{

                do{
                    System.out.print("\nPassword   :  ");
                    String pw = input.next();

                    boolean isCorrected = checkPassword(pw);

                    if(isCorrected == false){
                        System.out.println("password is incorrect. please try again!");
                        continue;
                    }else if(isCorrected == true){
                        clearConsole();
                        homePage();
                    }
                }while(true);
            }
        }while(true);
    }

    public static void main(String args[]){

        loginPage();
    }

}
