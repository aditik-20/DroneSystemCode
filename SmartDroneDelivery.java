import java.util.Scanner;

class DeliveryNode {
    String item;
    int priority;
    String location;
    DeliveryNode prev, next;

    DeliveryNode(String item, int priority, String location) {
        this.item = item;
        this.priority = priority;
        this.location = location;
        this.prev = this.next = null;
    }
}

class DroneDeliveryDLL {
    private DeliveryNode head = null, tail = null;

    public void addDelivery(String item, int priority, String location) {
        DeliveryNode newNode = new DeliveryNode(item, priority, location);

        if (head == null) {
            head = tail = newNode;
        } else if (priority < head.priority) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            DeliveryNode ptr = head;
            while (ptr.next != null && ptr.next.priority <= priority) {
                ptr = ptr.next;
            }
            newNode.next = ptr.next;
            if (ptr.next != null) {
                ptr.next.prev = newNode;
            } else {
                tail = newNode;
            }
            ptr.next = newNode;
            newNode.prev = ptr;
        }
        System.out.println("Added Delivery: " + item + 
                           " (Priority " + priority + 
                           ", Location: " + location + ")");
    }

    public void dispatchDelivery() {
        if (head == null) {
            System.out.println("No deliveries to dispatch");
            return;
        }
        System.out.println("Dispatching: " + head.item + 
                           " (Priority " + head.priority + 
                           ") to " + head.location);
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
    }

    public void displayDeliveries() {
        if (head == null) {
            System.out.println("No pending deliveries");
            return;
        }
        System.out.println("Pending Deliveries:");
        DeliveryNode ptr = head;
        while (ptr != null) {
            System.out.println("- " + ptr.item +
                               " (Priority " + ptr.priority +
                               ", Location: " + ptr.location + ")");
            ptr = ptr.next;
        }
    }
}

public class SmartDroneSystemDLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DroneDeliveryDLL queue = new DroneDeliveryDLL();

        while (true) {
            System.out.println("\n--- Drone Delivery System Menu ---");
            System.out.println("1. Add Delivery");
            System.out.println("2. Dispatch Delivery");
            System.out.println("3. Display Pending Deliveries");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String item = sc.nextLine();
                    System.out.print("Enter priority (smaller = higher priority): ");
                    int priority = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter location: ");
                    String location = sc.nextLine();
                    queue.addDelivery(item, priority, location);
                    break;

                case 2:
                    queue.dispatchDelivery();
                    break;

                case 3:
                    queue.displayDeliveries();
                    break;

                case 4:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
