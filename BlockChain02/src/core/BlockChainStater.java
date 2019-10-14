package core;
 
import java.util.ArrayList;
import util.Util;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
 
public class BlockChainStater {
 
    public static void main(String[] args) throws Exception {
    
        // 무작위의 개인키와 공개키를 생성하기 위해 키 생성 객체 정의
        KeyPairGenerator kpg;
        kpg = KeyPairGenerator.getInstance("EC", "SunEC");
        
        // 타원 곡선 디지털 서명 알고리즘 객체를 생성
        ECGenParameterSpec ecsp;
        // 세부 알고리즘 스펙을 정의
        ecsp = new ECGenParameterSpec("sect163k1");
        // 랜덤으로 임의의 키를 생성
        kpg.initialize(ecsp, new SecureRandom());
        
        // A의 개인키와 공개키 한 쌍을 생성
        KeyPair kp1 = kpg.genKeyPair();
        PrivateKey privKey1 = kp1.getPrivate();
        PublicKey pubKey1 = kp1.getPublic();
        
        // B의 개인키와 공개키 한 쌍을 생
        KeyPair kp2 = kpg.genKeyPair();
        PrivateKey privKey2 = kp2.getPrivate();
        PublicKey pubKey2 = kp2.getPublic();
        
        // 서명 객체를 생성해 A의 개인키를 설정
        Signature ecdsa1;
        ecdsa1 = Signature.getInstance("SHA1withECDSA", "SunEC");
        ecdsa1.initSign(privKey1);
    
        // 서명 객체를 생성해 B의 개인키를 설정
        Signature ecdsa2;
        ecdsa2 = Signature.getInstance("SHA1withECDSA", "SunEC");
        ecdsa2.initSign(privKey2);
 
        // 임의의 원래 문장을 정의
        String text = "A가 C에게 100코인을 전송";
        System.out.println("원래 문장: " + text);
        
        // 변경된 원래 문장을 정의
        String textInfected = "A가 B에게 100코인을 전송";
        System.out.println("변경된 문장: " + textInfected);
        
        // 원래 문장에 대해 암호화를 수행해 서명 값(암호문)을 얻어낸다.
        ecdsa1.update(text.getBytes("UTF-8"));
        byte[] signatureByte1 = ecdsa1.sign();
        System.out.println("암호문: 0x" + (new BigInteger(1,signatureByte1).toString(16)).toUpperCase());
        
        // 변경된 문장에 대해 암호화를 수행해 서명 값(암호문)을 얻어낸다.
        ecdsa2.update(textInfected.getBytes("UTF-8"));
        byte[] signatureByte2 = ecdsa2.sign();
        System.out.println("암호문: 0x" + (new BigInteger(1,signatureByte2).toString(16)).toUpperCase());
        
        // 서명 객체를 생성해 A의 공개키를 이용해 복호화할 수 있도록 설정
        Signature signature1;
        signature1 = Signature.getInstance("SHA1withECDSA", "SunEC");
        signature1.initVerify(pubKey1);
        
        // 서명 객체를 생성해 B의 공개키를 이용해 복호화할 수 있도록 설정
        Signature signature2;
        signature2 = Signature.getInstance("SHA1withECDSA", "SunEC");
        signature2.initVerify(pubKey2);
        
        // 원래 문장을 공개키로 복호화해 검증
        signature1.update(text.getBytes("UTF-8"));
        System.out.println("원래 문장 검증 (A의 publc key): " + signature1.verify(signatureByte1));
        
        signature1.update(textInfected.getBytes("UTF-8"));
        System.out.println("변경된 문장 검증 (A의 publc key): " + signature1.verify(signatureByte2));
        
        signature2.update(text.getBytes("UTF-8"));
        System.out.println("원래 문장 검증 (B의 publc key): " + signature2.verify(signatureByte1));
        
        signature2.update(textInfected.getBytes("UTF-8"));
        System.out.println("변경된 문장 검증 (B의 publc key): " + signature2.verify(signatureByte2));
        
    }
}
