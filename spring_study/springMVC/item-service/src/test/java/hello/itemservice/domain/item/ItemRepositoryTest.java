package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item saveItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);
        //when

        Item saveItem1 = itemRepository.save(item1);
        Item saveItem2 = itemRepository.save(item2);
        List<Item> items = itemRepository.findAll();


        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(saveItem1, saveItem2);
    }

    @Test
    void update() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item1);


        //when
        Item updateParam = new Item("item2", 20000, 20);
        itemRepository.update(savedItem.getId(), updateParam);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
        assertThat(findItem.getId()).isEqualTo(savedItem.getId());
    }




}