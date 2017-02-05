package knowhere.mx.milanopartner.utils;

/**
 * Created by cacorona on 12/07/2016.
 */
public class MilanoEnums {


    public enum MenuIcon {
        HomeIcon(0),
        FinacesIcon(1),
        RateIcon(2),
        AccountIcon(3),
        Unknown(4);

         MenuIcon(int opcode) {
            this.opcode = opcode;
        }

        private int opcode;

        public int GetOpCode() {
            return opcode;
        }
        public static MenuIcon GetValue(int _id) {
            MenuIcon[] As = MenuIcon.values();
            for (int i = 0; i < As.length; i++) {
                if (As[i].GetOpCode() == _id)
                    return As[i];
            }
            return MenuIcon.Unknown;
        }
    }

    public enum TimePeriod{
        PerWeek(0),
        PerMonth(1),
        PerYear(2),
        AllTime(3);

        private int opcode;

        TimePeriod(int opcode){
            this.opcode = opcode ;
        }
        public int GetOpCode()
        {
            return opcode;
        }
        public static TimePeriod GetValue(int _id) {
            TimePeriod[] As = TimePeriod.values();
            for (int i = 0; i < As.length; i++) {
                if (As[i].GetOpCode() == _id)
                    return As[i];
            }
            return null;
        }
    }

    public enum Service {
        Comments(0),
        Advices(1),
        Unknown(99);

        Service(int opcode) {
            this.opcode = opcode;
        }

        private int opcode;

        public int GetOpCode() {
            return opcode;
        }
        public static Service GetValue(int _id) {
            Service[] As = Service.values();
            for (int i = 0; i < As.length; i++) {
                if (As[i].GetOpCode() == _id)
                    return As[i];
            }
            return Service.Unknown;
        }
    }

    public enum HttpCode{
        OK(200);

        private int opccode;
        HttpCode(int opcode){
            this.opccode = opcode;
        }
        public int getOpCode(){
            return opccode;
        }

    }
}
