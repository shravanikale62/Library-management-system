package com.library.librarymanagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueBookRepository extends JpaRepository<IssueBook, Integer> {

    List<IssueBook> findByStudentName(String studentName);
}