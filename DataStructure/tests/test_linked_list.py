import logging

import pytest

from DataStructure import LinkedList


class TestLinkedList:
    def test_create_node_should_generate_a_node_with_input_value_and_null_next(self):
        node = LinkedList.Node(1)
        assert node.data == 1
        assert node.next is None

    def test_create_linked_list_should_generate_a_linked_list_with_proper_head(self):
        linked_list_with_head = LinkedList.instantiate(LinkedList.Types.HEADER)
        assert linked_list_with_head.head is not None
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next is None
        assert linked_list_with_head.length == 0

        linked_list_without_head = LinkedList.instantiate(LinkedList.Types.NON_HEADER)
        assert linked_list_without_head.head is None
        assert linked_list_without_head.length == 0

    def test_insert_should_insert_a_node_to_linked_list_at_position_0(self):
        linked_list_with_head = LinkedList.instantiate(LinkedList.Types.HEADER)
        linked_list_with_head.insert(0, 10)
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next.data == 10
        assert linked_list_with_head.length == 1

        linked_list_without_head = LinkedList.instantiate(LinkedList.Types.NON_HEADER)
        linked_list_without_head.insert(0, 10)
        assert linked_list_without_head.head.data == 10
        assert linked_list_without_head.length == 1

    def test_insert_should_insert_a_node_to_linked_list_at_last(self):
        linked_list_with_head = LinkedList.instantiate(LinkedList.Types.HEADER)
        linked_list_with_head.insert(0, 10)
        linked_list_with_head.insert(1, 20)
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next.data == 10
        assert linked_list_with_head.head.next.next.data == 20
        assert linked_list_with_head.length == 2

        linked_list_without_head = LinkedList.instantiate(LinkedList.Types.NON_HEADER)
        linked_list_without_head.insert(0, 10)
        linked_list_without_head.insert(1, 20)
        assert linked_list_without_head.head.data == 10
        assert linked_list_without_head.head.next.data == 20
        assert linked_list_without_head.length == 2

    def test_insert_should_raise_exception_when_position_is_invalid(self):
        linked_list_with_head = LinkedList.instantiate(LinkedList.Types.HEADER)
        with pytest.raises(ValueError) as e:
            linked_list_with_head.insert(2, 10)
            assert str(e.value) == "Invalid position"

        linked_list_without_head = LinkedList.instantiate(LinkedList.Types.NON_HEADER)
        with pytest.raises(ValueError) as e:
            linked_list_without_head.insert(2, 10)
            assert str(e.value) == "Invalid position"

    def test_delete_should_delete_a_node_from_linked_list_at_position_0(self):
        linked_list_with_head = LinkedList.from_list([10, 20], LinkedList.Types.HEADER)
        linked_list_with_head.delete(0)
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next.data == 20
        assert linked_list_with_head.length == 1

        linked_list_without_head = LinkedList.from_list([10, 20], LinkedList.Types.NON_HEADER)
        linked_list_without_head.delete(0)
        assert linked_list_without_head.head.data == 20
        assert linked_list_without_head.length == 1

    def test_delete_should_delete_a_node_from_linked_list_at_position(self):
        linked_list_with_head = LinkedList.from_list([10, 20, 30], LinkedList.Types.HEADER)
        linked_list_with_head.delete(1)
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next.data == 10
        assert linked_list_with_head.head.next.next.data == 30
        assert linked_list_with_head.length == 2

        linked_list_without_head = LinkedList.from_list([10, 20, 30], LinkedList.Types.NON_HEADER)
        linked_list_without_head.delete(1)
        assert linked_list_without_head.head.data == 10
        assert linked_list_without_head.head.next.data == 30
        assert linked_list_without_head.length == 2

    def test_delete_should_raise_exception_when_position_is_invalid(self):
        linked_list_with_head = LinkedList.from_list([10, 20], LinkedList.Types.HEADER)
        with pytest.raises(ValueError) as e:
            linked_list_with_head.delete(2)
            assert str(e.value) == "Invalid position"

        linked_list_without_head = LinkedList.from_list([10, 20], LinkedList.Types.NON_HEADER)
        with pytest.raises(ValueError) as e:
            linked_list_without_head.delete(2)
            assert str(e.value) == "Invalid position"

    def test_pop_should_delete_the_last_node_from_linked_list(self):
        linked_list_with_head = LinkedList.from_list([10, 20], LinkedList.Types.HEADER)
        linked_list_with_head.pop()
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next.data == 10
        assert linked_list_with_head.length == 1

        linked_list_without_head = LinkedList.from_list([10, 20], LinkedList.Types.NON_HEADER)
        linked_list_without_head.pop()
        assert linked_list_without_head.head.data == 10
        assert linked_list_without_head.length == 1

    def test_append_should_insert_a_node_to_linked_list_at_last(self):
        linked_list_with_head = LinkedList.from_list([10], LinkedList.Types.HEADER)
        linked_list_with_head.append(20)
        assert linked_list_with_head.head.data == 0
        assert linked_list_with_head.head.next.data == 10
        assert linked_list_with_head.head.next.next.data == 20
        assert linked_list_with_head.length == 2

        linked_list_without_head = LinkedList.from_list([10], LinkedList.Types.NON_HEADER)
        linked_list_without_head.append(20)
        assert linked_list_without_head.head.data == 10
        assert linked_list_without_head.head.next.data == 20
        assert linked_list_without_head.length == 2

    def test_str_should_return_string_representation_of_linked_list(self):
        linked_list_with_head = LinkedList.from_list([10, 20, 30], LinkedList.Types.HEADER)
        assert str(linked_list_with_head) == "10 -> 20 -> 30"

        linked_list_without_head = LinkedList.from_list([10, 20, 30], LinkedList.Types.NON_HEADER)
        assert str(linked_list_without_head) == "10 -> 20 -> 30"

    def test_find_should_return_position_of_data_in_linked_list(self):
        linked_list_with_head = LinkedList.from_list([10, 20, 30], LinkedList.Types.HEADER)
        assert linked_list_with_head.find(20) == 1
        assert linked_list_with_head.find(200) is None

        linked_list_without_head = LinkedList.from_list([10, 20, 30], LinkedList.Types.NON_HEADER)
        assert linked_list_without_head.find(30) == 2
        assert linked_list_without_head.find(200) is None

    def test_getitem_should_return_data_at_position(self):
        linked_list_with_head = LinkedList.from_list([10, 20, 30], LinkedList.Types.HEADER)
        assert linked_list_with_head[1] == 20

        linked_list_without_head = LinkedList.from_list([10, 20, 30], LinkedList.Types.NON_HEADER)
        assert linked_list_without_head[2] == 30

    def test_getitem_should_raise_exception_when_position_is_invalid(self):
        linked_list_with_head = LinkedList.from_list([10, 20], LinkedList.Types.HEADER)
        with pytest.raises(ValueError) as e:
            _ = linked_list_with_head[2]
            assert str(e.value) == "Invalid position"

        linked_list_without_head = LinkedList.from_list([10, 20], LinkedList.Types.NON_HEADER)
        with pytest.raises(ValueError) as e:
            _ = linked_list_without_head[2]
            assert str(e.value) == "Invalid position"

    def test_repr_should_return_string_representation_of_linked_list(self):
        linked_list_with_head = LinkedList.from_list([10, 20, 30], LinkedList.Types.HEADER)
        logging.info(linked_list_with_head.__repr__())
        linked_list_without_head = LinkedList.from_list([10, 20, 30], LinkedList.Types.NON_HEADER)
        logging.info(linked_list_without_head.__repr__())
