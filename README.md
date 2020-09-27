"# bhp-java-sdk" 
####Create a private key and generate a corresponding public key and address
```java
import com.bhp.sdk.bean.BhpAccount;
public class CreateAccount{
    public static void main(String[] args){
        BhpAccount bhpAccount = BhpAccount
            .createAccount()
            .hasMnemonic(true) //Generate a new mnemonic phrase and generate a corresponding private key and public key and address
            .build(); 
    }

}
```
####signature

```java
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bhp.sdk.bean.Signer;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

public class Signature{

    public static void main(String[] args){
        Amount amount = new Amount("abhp","5000000");
        
                Fee fee = new Fee("200000", Collections.singletonList(amount));
        
                Amount sendAmount = new Amount("abhp","10000000000");
        
                JSONArray jsonArray = new JSONArray().fluentAdd(sendAmount);
        
                JSONObject value = new JSONObject()
                        .fluentPut("amount", jsonArray)
                        .fluentPut("from_address", "bhp1arwh8ddg37fphh732yggmhkgk6gfjc97mckg0d")
                        .fluentPut("to_address", "bhp1pw86qx0mlma5ajl46wjwgn34k3tjr2a7azg4ad");
        
        
                JSONObject msg = new JSONObject()
                        .fluentPut("type", "cosmos-sdk/MsgSend")
                        .fluentPut("value", value);
        
                try
                {
                    String sign = Signer.createSigner()
                            .chainId("testing")
                            .memo("")
                            .sequence("400")
                            .accountNumber("16")
                            .fee(fee)
                            .msg(new JSONArray().fluentAdd(msg))
                            .privateKey("d47fe557084d7a7a2fe1d4de90385e5cbc0a62b2c5ee1d12f474a0376c958447")
                            .sign();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
    }

}




```