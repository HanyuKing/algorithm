-- testP175
select p.firstName, p.lastName, a.city, a.state from Address a left join Person p on p.personId = a.personId

-- testP176
select max(salary) from employee a, (select max(salary) as maxSalary from employee) b where a.salary < b.maxSalary;

-- testP177
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
set N = N - 1;
RETURN (
        # Write your MySQL query statement below.
        select distinct salary from Employee order by salary desc limit N, 1
    );
END

-- testP178
select b.score, c.rank from scores b left join (
    select ROW_NUMBER() over(order by a.score desc) as 'rank',
            score from (select distinct score from scores) a
) c on b.score = c.score order by b.score desc

-- testP180
select distinct l1.num as ConsecutiveNums from Logs l1, Logs l2, Logs l3 where l1.id = l2.id + 1 and l2.id = l3.id + 1 and l1.num = l2.num and l2.num = l3.num

-- testP181
select e.name as Employee from Employee e left join Employee m on e.managerId = m.id and e.salary > m.salary

-- testP182
select email as Email from Person group by email having count(id) > 1;

-- testP183
-- select name as Customers from Customers where id not in (select customer_id from Orders)
select c.name as Customers from Customers c left join Orders o on c.id = o.customerId where o.customerId is Null

-- testP184
SELECT
    d.NAME AS Department,
    e.NAME AS Employee,
    e.salary AS Salary
FROM
    Employee e,
    Department d,
    ( SELECT departmentId, max( salary ) AS salary FROM Employee GROUP BY departmentId ) a
WHERE
    e.departmentId = a.departmentId
  AND e.salary = a.salary
  AND d.id = a.departmentId

-- testP185
select top 3 from Employee