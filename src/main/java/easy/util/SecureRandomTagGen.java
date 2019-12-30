package easy.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class SecureRandomTagGen {
  private SecureRandom random = new SecureRandom();

  public String getTagNumber() {
    return new BigInteger(50, random).toString(32);
  }
}