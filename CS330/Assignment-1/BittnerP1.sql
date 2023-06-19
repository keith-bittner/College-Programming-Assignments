--  Name:   Keith Bittner
--  you can put other comments here if you so desire

use employees;

select 'running query for problem 1, results:' as '=============================';
	SELECT count(*)
	FROM employees
	WHERE (first_name = "Hugo"
		AND last_name = "Peng");

select 'running query for problem 2, results:' as '=============================';
	SELECT count(*)
	FROM employees
	WHERE (hire_date > "1995-01-01");

select 'running query for problem 3, results:' as '=============================';
	SELECT count(*)
	FROM employees
	WHERE (hire_date >= "1995-01-01");

select 'running query for problem 4, results:' as '=============================';
	SELECT count(emp_no)
	FROM dept_emp
	WHERE (from_date >= "1980-01-01"
		AND to_date <= "1985-12-31");

select 'running query for problem 5, results:' as '=============================';
	SELECT count(*)
	FROM dept_manager
	WHERE (to_date = "9999-01-01");

select 'running query for problem 6, results:' as '=============================';
	SELECT count(*)
	FROM employees
	WHERE (last_name
		LIKE "%good");

select 'running query for problem 7, results:' as '=============================';
	SELECT count(*)
	FROM employees
	WHERE (last_name
		LIKE "%good"
		AND first_name
		LIKE "M%");

select 'running query for problem 8, results:' as '=============================';
	SELECT from_date
	FROM dept_manager
	WHERE emp_no =
		(SELECT emp_no
		FROM employees
		WHERE (first_name = "Yuchang"
			AND last_name = "Weedman"));

select 'running query for problem 9, results:' as '=============================';
	SELECT title
	FROM titles
	WHERE to_date != "9999-01-01"
		AND emp_no =
			(SELECT emp_no
			FROM employees
			WHERE first_name = "Yuchang"
				AND last_name = "Weedman");

select 'running query for problem 10, results:' as '=============================';
	SELECT count(*)
	FROM salaries
	WHERE (salary > 150000
		AND to_date = "9999-01-01");

select 'running query for problem 11, results:' as '=============================';
	SELECT count(salary)-1
	FROM salaries
	WHERE emp_no =
		(SELECT emp_no
		FROM employees
		WHERE first_name = "Lidong"
			AND last_name = "Meriste");

select 'running query for problem 12, results:' as '=============================';
	SELECT e.first_name, e.last_name, s.salary
	FROM employees e, salaries s
	WHERE e.emp_no = s.emp_no
		AND s.salary =
			(SELECT max(salary)
			FROM salaries s);

select 'running query for problem 13, results:' as '=============================';
	SELECT e.first_name, e.last_name, s.salary
	FROM employees e, salaries s
	WHERE e.emp_no = s.emp_no
		AND s.salary =
			(SELECT min(salary)
			FROM salaries s);

select 'running query for problem 14, results:' as '=============================';
	SELECT e.first_name, e.last_name, s.salary
	FROM salaries s, employees e
	WHERE e.emp_no = s.emp_no
		AND s.salary =
			(SELECT min(salary)
			FROM salaries s, employees e
			WHERE e.gender = "F"
				AND e.emp_no = s.emp_no
				AND s.to_date ="9999-01-01");

select 'running query for problem 15, results:' as '=============================';
	SELECT e.first_name, e.last_name, datediff(d.to_date, e.hire_date)
		AS num_days
	FROM employees e
	INNER JOIN dept_emp d
		ON e.emp_no = d.emp_no
	WHERE datediff(d.to_date, e.hire_date) =
		(SELECT max(datediff(d.to_date, e.hire_date))
		FROM employees e
		INNER JOIN dept_emp d
			ON e.emp_no = d.emp_no
		WHERE datediff(d.to_date, e.hire_date)
			IN
			(SELECT datediff(d.to_date, e.hire_date)
			FROM employees e
			INNER JOIN dept_emp d
				ON e.emp_no = d.emp_no
			WHERE d.to_date != "9999-01-01"));
