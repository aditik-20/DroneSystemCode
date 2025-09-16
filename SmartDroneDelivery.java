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

class DroneDeliveryQueue {
    private DeliveryNode head = null;

    public void addDelivery(String item, int priority, String location) {
        DeliveryNode newNode = new DeliveryNode(item, priority, location);
        if (head == null) {
            head = newNode;
        } else {
            DeliveryNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void dispatchDelivery() {
        if (head == null) {
            System.out.println("No deliveries to dispatch");
            return;
        }
        System.out.println("Dispatching: " + head.item + " to " + head.location);
        head = head.next;
    }

    public void displayDeliveries() {
        if (head == null) {
            System.out.println("No pending deliveries");
            return;
        }
        System.out.println("Pending Deliveries:");
        DeliveryNode temp = head;
        while (temp != null) {
            System.out.println("- " + temp.item +
                               " (Priority: " + temp.priority +
                               ", Location: " + temp.location + ")");
            temp = temp.next;
        }
    }
}

public class SmartDroneDelivery {
    public static void main(String[] args) {
        DroneDeliveryQueue queue = new DroneDeliveryQueue();

        queue.addDelivery("Medicines", 1, "Sector 5");
        queue.addDelivery("Fresh Vegetables", 2, "Sector 9");
        queue.addDelivery("Lab Samples", 1, "Sector 3");

        queue.displayDeliveries();
        queue.dispatchDelivery();
        queue.displayDeliveries();
    }
}
