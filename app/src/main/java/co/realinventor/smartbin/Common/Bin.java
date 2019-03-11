package co.realinventor.smartbin.Common;

public class Bin {
    public boolean stat;
    public long amount,limit;

    public Bin() {
    }

    public boolean getStat() {
        return stat;
    }

    public void setStat(boolean stat) {
        this.stat = stat;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }
}
