package com.dragon.token.generate;

import com.dragon.token.TokenBuilder;
import com.dragon.token.TokenParser;

/**
 * @ClassName: Token
 * @Description: Token
 * @Author: pengl
 * @Date: 2020/4/1 20:47
 * @Version V1.0
 */
public interface Token {
    /**
     * @MethodName: create
     * @Description: create a token string
     * @Author: pengl
     * @Date: 2020/4/1 20:49
     * @Version V1.0
     */
    String create(TokenBuilder builder);

    /**
     * @MethodName: verify
     * @Description: verify a token is valid
     * @Author: pengl
     * @Date: 2020/4/1 20:49
     * @Version V1.0
     */
    boolean verify(TokenParser parser);

    /**
     * @MethodName: parse
     * @Description: parse a token ,return a bean
     * @Author: pengl
     * @Date: 2020/4/1 20:50
     * @Version V1.0
     */
    <T> T parse(TokenParser parser);

}
