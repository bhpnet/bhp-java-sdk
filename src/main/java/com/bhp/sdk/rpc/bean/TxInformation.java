package com.bhp.sdk.rpc.bean;

import com.bhp.sdk.constant.BaseConstant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Trigger
 */
@SuppressWarnings("unchecked")
public class TxInformation {
    private static final String AUTO_GAS = "auto";
    private static final String DEFAULT_ADJUSTMENT = "2";

    private String from;
    private String memo;
    private String chainId;
    private String accountNumber;
    private String sequence;
    private String gas;
    private String gasAdjustment;
    private List<Amount> fees;
    private List<Amount> gasPrices;
    private boolean simulate;
    private List<Amount> amounts;

    public static AutoGasBuilder createTxInfoAutoCalculationGas() {
        return new AutoGasBuilder(
                AUTO_GAS,
                DEFAULT_ADJUSTMENT,
                Collections.singletonList(new Amount(BaseConstant.DEFAULT_DEANOM,BaseConstant.DEFAULT_AMOUNT))
        );
    }

    public static CustomGasBuilder createTxInformation() {
        return new CustomGasBuilder();
    }

    private void init(Builder builder) {
        this.from = builder.from;
        this.memo = builder.memo;
        this.chainId = builder.chainId;
        this.accountNumber = builder.accountNumber;
        this.sequence = builder.sequence;
        this.amounts = builder.amounts;
        this.simulate = builder.simulate;
    }

    private TxInformation(AutoGasBuilder autoGasBuilder) {
        init(autoGasBuilder);
        this.fees = Collections.emptyList();
        this.gas = autoGasBuilder.gas;
        this.gasAdjustment = autoGasBuilder.gasAdjustment;
        this.gasPrices = autoGasBuilder.gasPrice;
    }

    private TxInformation(CustomGasBuilder customGasBuilder) {
        init(customGasBuilder);
        this.fees = customGasBuilder.fees;
    }

    public String getFrom() {
        return from;
    }

    public String getMemo() {
        return memo;
    }

    public String getChainId() {
        return chainId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSequence() {
        return sequence;
    }

    public String getGas() {
        return gas;
    }

    public String getGasAdjustment() {
        return gasAdjustment;
    }

    public List<Amount> getFees() {
        return Collections.unmodifiableList(fees);
    }

    public boolean isSimulate() {
        return simulate;
    }

    public List<Amount> getGasPrices() {
        return Collections.unmodifiableList(gasPrices);
    }

    public List<Amount> getAmounts() {
        return Collections.unmodifiableList(amounts);
    }

    public abstract static class Builder<T extends Builder<T>> {
        private String from;
        private String memo;
        private String chainId;
        private String accountNumber;
        private String sequence;
        private boolean simulate;
        private List<Amount> amounts = new ArrayList<>();
        private boolean autoCalculationFee;


        public T from(String from) {
            this.from = from;
            return (T) this;
        }

        public T memo(String memo) {
            this.memo = memo;
            return (T) this;
        }

        public T chainId(String chainId) {
            this.chainId = chainId;
            return (T) this;
        }

        public T accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return (T) this;
        }

        public T sequence(String sequence) {
            this.sequence = sequence;
            return (T) this;
        }

        public abstract TxInformation build();


        public T simulate(boolean simulate) {
            this.simulate = simulate;
            return (T) this;
        }

        public T amounts(List<Amount> amounts) {
            if (null == amounts || amounts.isEmpty()) {
                throw new IllegalArgumentException("amounts must not be empty");
            }
            this.amounts.addAll(amounts);
            return (T) this;
        }

        public T amount(Amount amount) {
            Optional.ofNullable(amount).orElseThrow(() -> new IllegalArgumentException("amount must not be empty"));
            amounts(Collections.singletonList(amount));
            return (T) this;
        }
    }

    public static class AutoGasBuilder extends Builder<AutoGasBuilder> {
        private String gas;
        private String gasAdjustment;
        private List<Amount> gasPrice;

        protected AutoGasBuilder(String gas,String gasAdjustment,List<Amount> gasPrice) {
            this.gas = gas;
            this.gasAdjustment = gasAdjustment;
            this.gasPrice = gasPrice;
        }

        public AutoGasBuilder gas(String gas) {
            this.gas = gas;
            return this;
        }

        public AutoGasBuilder gasAdjustment(String gasAdjustment) {
            this.gasAdjustment = gasAdjustment;
            return this;
        }

        @Override
        public TxInformation build() {
            return new TxInformation(this);
        }
    }

    public static class CustomGasBuilder extends Builder<CustomGasBuilder> {
        private List<Amount> fees = new ArrayList<>();

        protected CustomGasBuilder() {
        }

        public Builder fees(List<Amount> fees) {
            if (null == fees || fees.isEmpty()) {
                throw new IllegalArgumentException("fees must not be empty");
            }
            this.fees.addAll(fees);
            return this;
        }

        public Builder fee(Amount fee) {
            Optional.ofNullable(fee).orElseThrow(() -> new IllegalArgumentException("fee must not be null"));
            fees(Collections.singletonList(fee));
            return this;
        }

        @Override
        public TxInformation build() {
            return new TxInformation(this);
        }
    }

}
