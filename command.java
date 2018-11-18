import java.util.List;
import java.util.ArrayList;

/* The Command interface (負責制定命令使用介面) */
public interface Command {
   void execute();
}





/* The Invoker class (負責儲存與呼叫命令) */
/**
 * Invoker 儲存好命令 (Command)，
 * 可以於指定的時間，再去通知 Receiver，
 * 也就是所謂的 『預約 (reservation)』。
 *
 * 例如: 『有一個白天必須維持不變的資料庫，資料異動只能在午夜到凌晨 1 點之間施行，
 * 如果等到半夜，才匆匆忙忙輸入所有命令，那也太可憐，
 * 何不提早輸入完所有命令，並且在午夜 自動執行？』
 *
 * 命令模式 (Command Pattern) 提供了這樣的能力。
 */
public class Switch {
   private List<Command> history = new ArrayList<Command>();

   public Switch() {
   }

   public void storeAndExecute(Command cmd) {
      this.history.add(cmd); // optional
      cmd.execute();
   }
}





/* The Receiver class (負責執行命令的內容) */
/* Receiver 可以專注於如何實現 */
public class Light {
   public Light() {
   }

   public void turnOn() {
      System.out.println("The light is on");
   }

   public void turnOff() {
      System.out.println("The light is off");
   }
}






/* The Command for turning on the light - ConcreteCommand #1
   (負責呼叫 Receiver 的對應操作，通常持有 Receiver 物件。) */
public class FlipUpCommand implements Command {
   private Light theLight;

   public FlipUpCommand(Light light) {
      this.theLight = light;
   }

   public void execute(){
      theLight.turnOn();
   }
}

/* The Command for turning off the light - ConcreteCommand #2
   (負責呼叫 Receiver 的對應操作，通常持有 Receiver 物件。) */
public class FlipDownCommand implements Command {
   private Light theLight;

   public FlipDownCommand(Light light) {
      this.theLight = light;
   }

   public void execute() {
      theLight.turnOff();
   }
}







/* The test class or client (負責建立 具體命令 並組裝 接收者) */
/* Client 只需要下命令，不需要知道如何實現 */
public class PressSwitch {
   public static void main(String[] args){
      /* No pattern */
      // Light lamp = new Light();
      // lamp.turnOn();
      // lamp.turnOFF();

      /**
       * Use command pattern
       *
       * 好處:
       * 1. 將 "引發命令的物件" 與 "實際執行操作的物件" 隔離開來。
       * 2. 系統邏輯 的隔離，大大的增加了擴充性，例如: 新增一個命令物件，並
       *    配置 欲呼叫的 Receiver 與其操作，即可增加新功能。
       * 3. 確保了程式碼的 覆用 (reuse)。
       *
       * 參考: https://notfalse.net/4/command-pattern
       */
      Light lamp = new Light(); // receiver (負責執行命令的內容)

      Command switchUp = new FlipUpCommand(lamp); // concrete command (負責呼叫 Receiver 的對應操作，通常持有 Receiver 物件。)
      Command switchDown = new FlipDownCommand(lamp); // concrete command (負責呼叫 Receiver 的對應操作，通常持有 Receiver 物件。)

      Switch mySwitch = new Switch(); // invoker (負責儲存與呼叫命令)

      try {
         if ("ON".equalsIgnoreCase(args[0])) {
            mySwitch.storeAndExecute(switchUp); // invoker 儲存與呼叫命令
         }
         else if ("OFF".equalsIgnoreCase(args[0])) {
            mySwitch.storeAndExecute(switchDown); // invoker 儲存與呼叫命令
         }
         else {
            System.out.println("Argument \"ON\" or \"OFF\" is required.");
         }
      } catch (Exception e) {
         System.out.println("Arguments required.");
      }
   }
}
