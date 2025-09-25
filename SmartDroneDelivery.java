import java.util.Scanner;

class DeliveryNode {
    String item;
    int priority;
    String location;
    DeliveryNode next;

    DeliveryNode(String item, int priority, String location) {
        this.item = item;
        this.priority = priority;
        this.location = location;
        this.next = null;
    }
}

class DroneDelivery {
    private DeliveryNode start = null;

    public void addDelivery(String item, int priority, String location) {
        DeliveryNode newNode = new DeliveryNode(item, priority, location);
        if (start == null) {
            start = newNode;
        } else {
            DeliveryNode ptr = start;
            while (ptr.next != null) {
                ptr = ptr.next;
            }
            ptr.next = newNode;
        }
        System.out.println("Added Delivery: " + item + " (Priority " + priority + ", Location: " + location + ")");
    }

    public void dispatchDelivery() {
        if (start == null) {
            System.out.println("No deliveries to dispatch");
            return;
        }

        DeliveryNode ptr = start;
        DeliveryNode prev = null;
        DeliveryNode highest = start;
        DeliveryNode highestPrev = null;

        while (ptr != null) {
            if (ptr.priority < highest.priority) {
                highest = ptr;
                highestPrev = prev;
            }
            prev = ptr;
            ptr = ptr.next;
        }

        if (highestPrev == null) {
            start = highest.next;
        } else {
            highestPrev.next = highest.next;
        }

        System.out.println("Dispatching: " + highest.item +
                           " (Priority " + highest.priority +
                           ") to " + highest.location);
    }

    public void displayDeliveries() {
        if (start == null) {
            System.out.println("No pending deliveries");
            return;
        }
        System.out.println("Pending Deliveries:");
        DeliveryNode ptr = start;
        while (ptr != null) {
            System.out.println("- " + ptr.item +
                               " (Priority " + ptr.priority +
                               ", Location: " + ptr.location + ")");
            ptr = ptr.next;
        }
    }
}

public class SmartDroneSystemUserInput{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DroneDelivery queue = new DroneDelivery();

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

