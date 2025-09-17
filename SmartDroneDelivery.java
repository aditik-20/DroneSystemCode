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
    }
    public void dispatchDelivery() {
        if (start == null) {
            System.out.println("No deliveries to dispatch");
            return;
        }
        System.out.println("Dispatching: " + start.item + " to " + start.location);
        start = start.next;
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
                               " (Priority: " + ptr.priority +
                               ", Location: " + ptr.location + ")");
            ptr = ptr.next;
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
