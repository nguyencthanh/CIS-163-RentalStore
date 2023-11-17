package Project3;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Random;

/***************************************************************************
 * Holds all the code that contain for the Rental Store. This contains
 * a constructor "MySingleWithTailLinkedList" which sets the top and tail
 * node to null. It also contains the size method that tells the total
 * amount of nodes in the list. It also contains the clear method with gets
 * rid of all the existing nodes in the list. It also contains the add method
 * which add new node inside the list. It also contains the remove method
 * which remove one node from the list. It also contains the get method
 * which returns a node's data without removing the node. It also has the
 * display method which display each node's data. It also has the toString
 * method which prints out each node's data and the total size of the list.
 *
 * @author Thanh Nguyen
 */
public class MySingleWithTailLinkedList implements Serializable
{
    private Node top;
    private Node tail;

    public MySingleWithTailLinkedList() {
        top = tail = null;
    }

    /*****************************************************
     * A method that tells how many total nodes there are in the list
     *
     * @return an integer that tells how many nodes there are.
     */
    // This method has been provided, and you are not permitted to modify
    public int size() {
        if (top == null)
            return 0;

        int total = 0;
        Node temp = top;
        while (temp.getNext() != null) {
            total++;
            temp = temp.getNext();
        }

        if (temp != tail)
            throw new RuntimeException
                    ("Tail is not pointing at the end of the list");
        else
            total++;

        return total;
    }

    // This method has been provided, and you are not permitted to modify

    /****************************************************
     * Remove all the nodes in the node list to make it become null
     */
    public void clear () {
        Random rand = new Random(13);
        while (size() > 0) {
            int number = rand.nextInt(size());
            remove(number);
        }
    }

    /***********************************************************************
     * Add a new rental to the node list sort by it due date, and it types
     * (Game or Console) while the Game is first than Console is the second
     *
     * @param rental the unit being rented
     */
    public void add(Rental rental) {
        // no list
        if (top == null) {
            tail = top = new Node(rental, null);
            return;
        }

        //Rental is a Game, Goes on Top
        if (rental instanceof Game && top.getData().dueBack.after
                (rental.dueBack)) {
            top = new Node(rental, top);
            return;
        }

        //Rental is a Game
        if (rental instanceof Game) {

            //Goes to the node before the add placement
            Node gameTemp = top;
            while (gameTemp.getData() instanceof Game
                    && gameTemp.getNext() != null
                    && gameTemp.getNext().getData() instanceof Game
                    && gameTemp.getData().dueBack.before(rental.dueBack)
                    && gameTemp.getNext().getData().dueBack.before
                    (rental.dueBack)) {
                gameTemp = gameTemp.getNext();
            }

            //Check to see if both of the nodes are equal in dueDate
            if (gameTemp.getNext() != null && gameTemp.getNext().getData().
                    dueBack.equals(rental.dueBack)) {
               while(gameTemp.getNext() != null && gameTemp.getNext().
                       getData().dueBack.equals(rental.dueBack)) {
                   if (rental.nameOfRenter.compareTo(gameTemp.getNext()
                           .getData().nameOfRenter) < 0) {
                       Node tempData = gameTemp;
                       tempData = new Node(rental, gameTemp.getNext());
                       gameTemp.setNext(tempData);
                       return;
                   }
                   gameTemp = gameTemp.getNext();
               }
            }
                //Place New Node
                Node tempData = new Node(rental, gameTemp.getNext());
            gameTemp.setNext(tempData);

                //Check to see if last node is null
                if (tempData.getNext() == null) {
                    tail = tempData;
                }
                return;
            }

        //Rental is a Console, and goes on top
        if (rental instanceof Console && top.getData().dueBack.
                after(rental.dueBack)) {
            top = new Node(rental, top);
            return;
        }


        //Rental is a Console
        if(rental instanceof Console){
            Node consoleTemp = top;

            //Goes pass all the Game
            while(consoleTemp.getData() instanceof Game
                    &&consoleTemp.getNext() != null
                    &&consoleTemp.getNext().getData()instanceof Game){
                consoleTemp = consoleTemp.getNext();
            }

            //No other console
            if(consoleTemp.getNext() == null){
                Node tempData = consoleTemp;
                tempData = new Node(rental, consoleTemp.getNext());
                consoleTemp.setNext(tempData);
                tail = consoleTemp.getNext();
                return;
            }


           // if first console is after the rental
            if(consoleTemp.getNext().getData() instanceof Console &&
                    consoleTemp.getNext().getData().dueBack.after
                            (rental.dueBack)){
                Node tempData = consoleTemp;
                tempData = new Node(rental, consoleTemp.getNext());
                consoleTemp.setNext(tempData);
                return;
            }

            //Goes to the node before add placement
            consoleTemp = consoleTemp.getNext();
            while(consoleTemp.getData()instanceof Console
                    && consoleTemp.getNext()!=null
                    && consoleTemp.getData().dueBack.before(rental.dueBack)
                    && consoleTemp.getNext().getData().dueBack.
                    before(rental.dueBack)){
                consoleTemp = consoleTemp.getNext();
            }

            //Check to see if both of nodes are equal
            if (consoleTemp.getNext() != null && consoleTemp.getData().
                    dueBack.equals(rental.dueBack)) {
                while(consoleTemp.getNext() != null && consoleTemp.getNext().getData()
                        .dueBack.equals(rental.dueBack)) {
                    if (consoleTemp.getData().nameOfRenter.compareTo
                            (rental.nameOfRenter) < 0) {
                        Node tempData = consoleTemp;
                        tempData = new Node(rental, consoleTemp.getNext());
                        consoleTemp.setNext(tempData);
                        return;
                    }
                    consoleTemp = consoleTemp.getNext();
                }
            }

            //Add new node
            Node tempData = consoleTemp;
            tempData = new Node(rental, consoleTemp.getNext());
            consoleTemp.setNext(tempData);

            //Check to see if last node is null
            if(consoleTemp.getNext().getNext() == null){
                tail = consoleTemp.getNext();
            }
            return;
        }
    }

    /***********************************************************************
     * A method to remove a node from the whole node list at
     * the location of what index it is at
     *
     * @param index an integer that tells what node's location to remove at
     * @return a rental that shows what the node's data is at that index
     */
    public Rental remove(int index) {
        //No List
        if(top == null){
            return null;
        }

        //Check Parameter
        if(index < 0 || index >= size()){
            return null;
        }

        //Top Of The List
        if(index == 0){
            Node temp = top;
            top = top.getNext();
            return temp.getData();
        }

        //Bottom Of The List
        if(index == size() - 1){
            Node temp = top;
            int i = 0;
            while(i < index - 1){
                temp = temp.getNext();
                i++;
            }
            Node tempData = temp.getNext();
            temp.setNext(null);
            tail = temp;
            return tempData.getData();
        } else {

            //Middle Of The List
            Node temp = top;
            int i = 0;
            while (i < index - 1) {
                i++;
                temp = temp.getNext();
            }
            Node tempData = temp.getNext();
            temp.setNext(temp.getNext().getNext());
            return tempData.getData();
        }
    }

    /***********************************************************************
     * Returns a rental from the parameter index that display the rental data
     * at that index's Node
     *
     * @param index an integer that tells what node's location to look at
     * @return a rental that shows what the node's data is at that index
     */
    public Rental get(int index) {
        //No List
        if(top == null){
            return null;
        }

        //Check Parameter
        if(index < 0 || index >= size()){
            return null;
        }

        //Top Of The List
        if(index == 0){
            return top.getData();
        } else {
            //On The List
            Node temp = top;
            int i = 0;
            while(i < index){
                i++;
                temp = temp.getNext();
            }
            return temp.getData();
        }
    }

    /***********************************************************************
     * A method that print out all the node one by one each of their data
     */
    public void display() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    /***********************************************************************
     * A toString that overrides the object's toString to display each
     * node's data and tell what the size of the whole node list.
     *
     * @return returns a string that display each node's data and
     * the size of the total whole nodes
     */
    @Override
    public String toString() {
        return "LL {" +
                "top=" + top +
                ", size=" + size() +
                '}';
    }
}

