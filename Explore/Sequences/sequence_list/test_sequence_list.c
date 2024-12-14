#include <stdio.h>
#include <stdlib.h>

#include "/opt/homebrew/Cellar/check/0.15.2/include/check.h"
#include "sequence_list.h"

START_TEST(test_initSeqList)
{
  const SeqList *actual = initSeqList(5);
  ck_assert_int_eq(actual->capacity, 5);
  ck_assert_int_eq(actual->length, 0);
  ck_assert_int_eq(sizeof(actual->data), sizeof(int *));
}
END_TEST

START_TEST(test_clear_should_free_all_variables) {
  SeqList *p = initSeqList(5);
  clear(p);
  ck_assert_int_eq(p->capacity, 0);
  ck_assert_int_eq(p->length, 0);
  ck_assert_ptr_null(p->data);
}

START_TEST(test_insert_should_return_1_when_seqlist_has_space_left) {
  SeqList *p = initSeqList(5);
  int result = insert(p, 0, 10);
  ck_assert_int_eq(result, 1);
  ck_assert_int_eq(p->length, 1);
  ck_assert_int_eq(*p->data, 10);

  result = insert(p, 0, 20);
  ck_assert_int_eq(result, 1);
  ck_assert_int_eq(p->length, 2);
  ck_assert_int_eq(p->data[0], 20);
  ck_assert_int_eq(p->data[1], 10);

  result = insert(p, 2, 30);
  ck_assert_int_eq(result, 1);
  ck_assert_int_eq(p->length, 3);
  ck_assert_int_eq(p->data[0], 20);
  ck_assert_int_eq(p->data[1], 10);
  ck_assert_int_eq(p->data[2], 30);

  result = insert(p, 1, 40);
  ck_assert_int_eq(result, 1);
  ck_assert_int_eq(p->length, 4);
  ck_assert_int_eq(p->data[0], 20);
  ck_assert_int_eq(p->data[1], 40);
  ck_assert_int_eq(p->data[2], 10);
  ck_assert_int_eq(p->data[3], 30);

  result = insert(p, 4, 50);
  ck_assert_int_eq(result, 1);
  ck_assert_int_eq(p->length, 5);
  ck_assert_int_eq(p->data[0], 20);
  ck_assert_int_eq(p->data[1], 40);
  ck_assert_int_eq(p->data[2], 10);
  ck_assert_int_eq(p->data[3], 30);
  ck_assert_int_eq(p->data[4], 50);
}

START_TEST(test_insert_should_return_0_when_pos_is_out_of_bound) {
    SeqList *p = initSeqList(5);
    const int result = insert(p, 6, 10);
    ck_assert_int_eq(result, 0);
}

START_TEST(test_insert_should_return_0_when_seqlist_is_full) {
    SeqList *p = initSeqList(5);
    insert(p, 0, 10);
    insert(p, 0, 20);
    insert(p, 2, 30);
    insert(p, 1, 40);
    insert(p, 4, 50);
    const int result = insert(p, 0, 60);
    ck_assert_int_eq(result, 0);
}

START_TEST(test_erase_should_return_1_when_seqlist_is_not_empty_and_pos_is_legal) {
  SeqList *p = initSeqList(5);
  insert(p, 0, 10);
  insert(p, 0, 20);
  insert(p, 2, 30);
  int result = erase(p, 0);
  ck_assert_int_eq(result, 1);
  ck_assert_int_eq(p->length, 2);
  ck_assert_int_eq(p->data[0], 10);
  ck_assert_int_eq(p->data[1], 30);

  result = erase(p, 1);
  ck_assert_int_eq(result, 1);
  ck_assert_int_eq(p->length, 1);
  ck_assert_int_eq(p->data[0], 10);

  result = erase(p, 0);
  ck_assert_int_eq(result, 1);
  ck_assert_int_eq(p->length, 0);
  ck_assert_int_eq(p->data[0], 0);
}

START_TEST(test_erase_should_return_0_when_seqlist_is_empty_or_pos_is_illegal) {
  SeqList *p = initSeqList(5);
  insert(p, 0, 10);
  insert(p, 0, 20);
  int result = erase(p, 2);
  ck_assert_int_eq(result, 0);
  ck_assert_int_eq(p->length, 2);
  ck_assert_int_eq(p->data[0], 20);
  ck_assert_int_eq(p->data[1], 10);

  erase(p, 1);
  erase(p, 0);
  result = erase(p, 0);
  ck_assert_int_eq(result, 0);
  ck_assert_int_eq(p->length, 0);
  ck_assert_int_eq(p->data[0], 0);
}

Suite *sequence_list_suite(void)
{
  Suite *s = suite_create("SequenceList");

  /* Core test case */
  TCase *tc_core = tcase_create("Core");

  tcase_add_test(tc_core, test_initSeqList);
  tcase_add_test(tc_core, test_clear_should_free_all_variables);
  tcase_add_test(tc_core, test_insert_should_return_1_when_seqlist_has_space_left);
  tcase_add_test(tc_core, test_insert_should_return_0_when_seqlist_is_full);
  tcase_add_test(tc_core, test_insert_should_return_0_when_pos_is_out_of_bound);
  tcase_add_test(tc_core, test_erase_should_return_1_when_seqlist_is_not_empty_and_pos_is_legal);
  suite_add_tcase(s, tc_core);

  return s;
}

int main(void)
{
  Suite *s = sequence_list_suite();
  SRunner *sr = srunner_create(s);

  srunner_run_all(sr, CK_NORMAL);
  const int number_failed = srunner_ntests_failed(sr);
  srunner_free(sr);
  return (number_failed == 0) ? EXIT_SUCCESS : EXIT_FAILURE;
}