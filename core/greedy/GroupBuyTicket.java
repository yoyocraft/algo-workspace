package core.greedy;

/**
 * 组团买票
 * 景区里一共有m个项目，景区的第i个项目有如下两个参数：
 * game[i] = { Ki, Bi }，Ki、Bi一定是正数
 * Ki代表折扣系数，Bi代表票价
 * 举个例子 : Ki = 2, Bi = 10
 * 如果只有1个人买票，单张门票的价格为 : Bi - Ki * 1 = 8
 * 所以这1个人游玩该项目要花8元
 * 如果有2个人买票，单张门票的价格为 : Bi - Ki * 2 = 6
 * 所以这2个人游玩该项目要花6 * 2 = 12元
 * 如果有5个人买票，单张门票的价格为 : Bi - Ki * 5 = 0
 * 所以这5个人游玩该项目要花5 * 0 = 0元
 * 如果有更多人买票，都认为花0元(因为让项目倒贴钱实在是太操蛋了)
 * 于是可以认为，如果有x个人买票，单张门票的价格为 : Bi - Ki * x
 * x个人游玩这个项目的总花费是 : max { x * (Bi - Ki * x), 0 }
 * 单位一共有n个人，每个人最多可以选1个项目来游玩，也可以不选任何项目
 * 所有员工将在明晚提交选择，然后由你去按照上面的规则，统一花钱购票
 * 你想知道自己需要准备多少钱，就可以应付所有可能的情况，返回这个最保险的钱数
 * 数据量描述 :
 * 1 <= M、N、Ki、Bi <= 10^5
 */
public class GroupBuyTicket {

    static class Game {
        int ki, bi, people;

        public Game(int ki, int bi) {
            this.ki = ki;
            this.bi = bi;
            this.people = 0;
        }

        /**
         * 如果再来一个人，可以得到多少钱
         */
        public int earn() {
            // bi - (people + 1) * ki : 当前的人，门票原价减少了，当前的门票价格
            // people * ki : 当前人的到来，之前的所有人，门票价格都再减去ki
            return bi - (people + 1) * ki - people * ki;
        }
    }

}