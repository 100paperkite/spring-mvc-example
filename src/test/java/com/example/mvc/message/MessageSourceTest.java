package com.example.mvc.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;
    
    @Test
    public void locale_이_없다면_기본인_한글로_표시한다() {
        //given // when
        String result = ms.getMessage("hello", null, null);

        //then
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    public void locale_이_영어면_영어로_표시한다() {
        //given // when
        String result = ms.getMessage("hello", null, Locale.ENGLISH);

        //then
        assertThat(result).isEqualTo("hello");
    }
    
    @Test
    public void locale_을_찾지_못하면_예외가_발생한다() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    public void locale_을_찾지_못한_경우_기본메시지가_있다면_해당_메시지를_출력해야_한다() {
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");

    }

    @Test
    public void 메시지에_인자를_넘길_수_있다() throws Exception {
        String message = ms.getMessage("hello.name", new Object[]{"Spring", null}, null);
        assertThat(message).isEqualTo("안녕 Spring");
    }

}
