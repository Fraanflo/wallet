package cl.bootcamp.AlkeWallet.model;

import lombok.Data;
/**
 * clase model de moneda
 */
@Data
public class Moneda {
private int currencyId;
private String currencyName;
private String currencySymbol;
}
