// package br.com.api.perinityapp.perinityapi.controller;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import br.com.api.perinityapp.perinityapi.dto.DepartmentStats;
// import br.com.api.perinityapp.perinityapi.repository.PersonRepository;
// import br.com.api.perinityapp.perinityapi.repository.TaskRepository;

// @RestController
// @RequestMapping("/departamentos")
// public class DepartmentController {
// @Autowired
// private PersonRepository personRepository;

// @Autowired
// private TaskRepository taskRepository;

// Lista departamentos e quantidade de pessoas e tarefas
// @GetMapping("/")
// public List<DepartmentStats> getDepartmentStats() {
// List<Object[]> departmentStats =
// personRepository.countPeopleAndTasksByDepartment();
// List<DepartmentStats> departmentStatsList = new ArrayList<>();

// for (Object[] row : departmentStats) {
// String department = (String) row[0];
// Long peopleCount = (Long) row[1];
// Long tasksCount = (Long) row[2];

// departmentStatsList.add(new DepartmentStats(department, peopleCount,
// tasksCount));
// }

// return departmentStatsList;
// }
// }
