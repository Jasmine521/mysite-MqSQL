/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mysite;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author WIN11
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public Utils utils(){
        return new Utils();
    }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<LocalDate>() {
            /**
             *
             */
            @Override
            public String print(LocalDate object, Locale locale) {
                //定义一个格式化器对象，把日期转换成字符串
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", locale);
                return dtf.format(object);
            }

            @Override
            public LocalDate parse(String text, Locale locale) throws ParseException {

                return LocalDate.parse(text);

            }
        });

        WebMvcConfigurer.super.addFormatters(registry); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
