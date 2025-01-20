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

-- testP179
