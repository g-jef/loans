INSERT INTO library_member VALUES
    ('LM-1001', 'Priya Shah'),
    ('LM-1002', 'Tom Ellis'),
    ('LM-1003', 'Aisha Bello');

INSERT INTO book VALUES
    (1,  '9780132350884', 'Clean Code',                          'Robert C. Martin'),
    (2,  '9780134685991', 'Effective Java',                      'Joshua Bloch'),
    (3,  '9780321356680', 'Java Concurrency in Practice',        'Brian Goetz'),
    (4,  '9780201633610', 'Design Patterns',                     'Erich Gamma'),
    (5,  '9780137081073', 'The Clean Coder',                     'Robert C. Martin'),
    (6,  '9780596009205', 'Head First Design Patterns',          'Eric Freeman'),
    (7,  '9780134757599', 'Refactoring',                         'Martin Fowler'),
    (8,  '9780321127426', 'Patterns of Enterprise Architecture',  'Martin Fowler'),
    (9,  '9781617294945', 'Spring in Action',                    'Craig Walls'),
    (10, '9780596520687', 'SQL Cookbook',                        'Anthony Molinaro');

INSERT INTO loan (book_id, member_ref, issued_on) VALUES
    (1, 'LM-1001', DATE '2026-09-01'),
    (2, 'LM-1001', DATE '2026-09-02'),
    (3, 'LM-1001', DATE '2026-09-03'),
    (4, 'LM-1001', DATE '2026-09-04'),
    (5, 'LM-1001', DATE '2026-09-05');
