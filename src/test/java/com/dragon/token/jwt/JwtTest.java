package com.dragon.token.jwt;

import com.dragon.token.TokenAlgorithm;
import com.dragon.token.TokenBuilder;
import com.dragon.token.TokenParser;
import com.dragon.token.compression.Compression;
import com.dragon.token.generate.TokenFactory;
import com.dragon.token.generate.TokenType;
import com.dragon.token.serialize.SerializeType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName: JwtTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/4/1 21:45
 * @Version V1.0
 */
public class JwtTest {
    long bg;
    long end;

    @Before
    public void befor() {
        bg = System.currentTimeMillis();
    }

    @After
    public void after() {
        end = System.currentTimeMillis();
        System.out.println("----------all times:>>>" + (end - bg) + " ms");
    }

    @Test
    public void t(){
        Stu stu = new Stu("Z-01202323","abc",12);
        long expire = 10 * 60 * 60 * 1000;
        String key = "Abs19292381sssssdd2121sk2ksuidse";
        String token = TokenFactory.getToken().create(TokenBuilder.builder().data(stu).key(key).expire(expire).build());
        System.out.println(token);
        System.out.println(token.length());

        stu = (Stu)TokenFactory.getToken().parse(TokenParser.builder().key(key).token(token).build());
        System.out.println(stu.getUid() + "--" + stu.getName() + "--" + stu.getAge());
    }

    @Test
    public void tt1() throws InterruptedException {
        Stu stu = new Stu("Z-01202323","hh",11);
        String token = TokenFactory.getToken(TokenType.CRYPTO_TOKEN).create(
                 TokenBuilder.builder()
                .data(stu)
                //指定压缩算法
                .compression(Compression.DEFLATE)
                //指定加密算法
                .algorithm(TokenAlgorithm.AES)
                //密钥
                .key("abc12123112313213456778843213431")
                //有效期
                .expire(3000)
                .build());


        System.out.println(token);
        System.out.println(token.length());
        //System.out.println(JwtToken.builder().compression(Compression.GZIP).algorithm(JwtAlgorithm.PBEWithSHA1AndDESede).key("abc12123112313213456778843213431").build().verify(token));


        //Thread.sleep(4000);

        Stu stut = (Stu)TokenFactory.getToken(TokenType.CRYPTO_TOKEN).parse(
                 TokenParser.builder()
                .algorithm(TokenAlgorithm.AES)
                .checkExpire(true)
                .key("abc12123112313213456778843213431")
                .compression(Compression.DEFLATE)
                .token(token)
                .dataType(Stu.class)
                .build());


        System.out.println(stut.getUid() + "--" + stut.getName() + "--" + stut.getAge());

    }

    @Test
    public void t2() throws InterruptedException {
        Stu stu = new Stu("Z-01202323","hh",11);
        String token = TokenFactory.getToken(TokenType.JWT_TOKEN).create(
                TokenBuilder.builder()
                        .data(stu)
                        .expire(3000)
                        .build());


        System.out.println(token);
        System.out.println(token.length());
        Stu stut = (Stu) TokenFactory.getToken(TokenType.JWT_TOKEN).parse(
                TokenParser.builder()
                        .token(token)
                        .checkExpire(true)
                        .dataType(Stu.class)
                        .build());

        System.out.println(stut.getUid() + "--" + stut.getName() + "--" + stut.getAge());
    }

}
