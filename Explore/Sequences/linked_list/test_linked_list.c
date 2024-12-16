#include <stdio.h>
#include <stdlib.h>

#include "/opt/homebrew/Cellar/check/0.15.2/include/check.h"
#include "linked_list.h"

START_TEST(test_initLinkedList)
{
    LinkedList *p = init(NULL);
    ck_assert_int_eq(p->length, 0);
    ck_assert_ptr_null(p->node);
    clear(p);

    LinkedList *q = init(create_node(5));
    ck_assert_int_eq(q->length, 1);
    ck_assert_int_eq(q->node->val, 5);
    ck_assert_ptr_null(q->node->next);

    clear(q);
    q = NULL;
}

END_TEST

START_TEST(test_clearLinkedList_should_only_retain_the_pointer_to_the_head)
{
    LinkedList *p = init(create_node(5));
    clear(p);
    ck_assert_int_eq(p->length, 0);
    p = NULL;
}

END_TEST

Suite *sequence_list_suite(void)
{
    Suite *s = suite_create("LinkedList");

    /* Core test case */
    TCase *tc_core = tcase_create("Core");

    tcase_add_test(tc_core, test_initLinkedList);
    tcase_add_test(tc_core, test_clearLinkedList_should_only_retain_the_pointer_to_the_head);
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
