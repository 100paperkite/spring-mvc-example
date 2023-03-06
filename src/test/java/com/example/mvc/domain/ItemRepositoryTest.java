package com.example.mvc.domain;

import com.example.mvc.domain.dto.ItemParamDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    public void 아이템_추가() throws Exception {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }
    
    @Test
    public void 아이템_갱신() throws Exception {
        //given
        Item item = new Item("itemA", 10000, 10);
        ItemParamDto paramDto = new ItemParamDto("itemB", 20000, 20);

        //when

        Item savedItem = itemRepository.save(item);
        itemRepository.update(savedItem.getId(), paramDto);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());

        assertThat(findItem.getItemName()).isEqualTo(paramDto.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(paramDto.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(paramDto.getQuantity());
    }
    
    @Test
    public void 아이템_목록_조회() throws Exception {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);

        //when
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}