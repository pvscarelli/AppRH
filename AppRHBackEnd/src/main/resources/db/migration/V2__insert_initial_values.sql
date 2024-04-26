

INSERT INTO jobs (id, name, description, expiration, salary)
VALUES 
    ('8c8c2278-b9d3-4f0e-b0b7-df8a823e7290', 'Desenvolvedor Java', 'Desenvolvimento de aplicativos Java', '2024-04-12', '5000'),
    ('e5b3bf22-25a4-4d2a-8b73-0f50149289bd', 'Gerente de Projetos', 'Gestão de projetos de desenvolvimento de software', '2024-04-12', '8000'),
    ('b1121e47-9d8b-4eeb-a867-29e1b97d3ff4', 'Engenheiro de Dados', 'Análise e processamento de dados', '2024-05-10', '6000'),
    ('aadaf527-31c4-48b7-a1bf-f03868950d2d', 'Desenvolvedor Frontend', 'Desenvolvimento de interfaces de usuário', '2024-05-15', '5500'),
    ('f5b19f9b-9f5b-4c90-9166-bec5db8f2f4c', 'Analista de Dados', 'Análise estatística e interpretação de dados', '2024-05-20', '6500'),
    ('e8c4b01f-4fc8-493e-8fd5-1c6cf3157250', 'Engenheiro de Software', 'Desenvolvimento e manutenção de software', '2024-05-25', '7000'),
    ('c3f23ab1-979b-43a7-b75f-6f1bf3a5d77a', 'Arquiteto de Soluções', 'Design de arquiteturas de software', '2024-06-01', '7500'),
    ('f1c6f382-6c16-4e67-a869-9337e477b27a', 'Analista de Segurança da Informação', 'Proteção de sistemas de informação', '2024-06-05', '7200'),
    ('aeeb2e43-ebd9-4fa5-8a54-8841b9d0e9a4', 'Engenheiro de DevOps', 'Implementação e automação de processos de desenvolvimento', '2024-06-10', '6800'),
    ('d44c609c-7e9d-4b9e-b1f7-2d3dc7f5888a', 'Especialista em Machine Learning', 'Desenvolvimento de algoritmos de aprendizado de máquina', '2024-06-15', '7800'),
    ('c0b42e5a-bb1d-4e3c-93c6-52239401b845', 'Desenvolvedor Full Stack', 'Desenvolvimento de front-end e back-end', '2024-06-20', '7200'),
    ('e1c8e56a-5a0a-4a34-b2e5-0d5738488f65', 'Scrum Master', 'Facilitação e suporte à equipe ágil', '2024-06-25', '8000');


INSERT INTO applicants (id, cpf, name, mail)
VALUES 
    ('3e0e8913-89fd-4b0b-8e0c-72f1728bb9f1', '123.456.789-00', 'João Silva', 'joao@example.com'),
    ('4f9ae312-d309-4c17-899f-8f1a7e0729b3', '987.654.321-00', 'Maria Souza', 'maria@example.com'),
    ('6c2453d7-d49a-48ac-b47c-f0b3c7565db1', '111.222.333-00', 'Pedro Santos', 'pedro@example.com'),
    ('e944f8c1-21b2-4a68-81c6-6a89eab889f3', '444.555.666-00', 'Ana Oliveira', 'ana@example.com'),
    ('f7f26f39-0044-4a21-aecc-46d38b8ad05a', '777.888.999-00', 'Mariana Lima', 'mariana@example.com'),
    ('6fe580a1-3656-4fc7-af3c-38a0dc6f4cd8', '222.333.444-00', 'Carlos Oliveira', 'carlos@example.com'),
    ('8fd4a30f-f676-4fd0-b8ae-f312cb786af7', '555.666.777-00', 'Juliana Rodrigues', 'juliana@example.com'),
    ('a08f26a9-c9d6-4078-8142-97f96cdd0fe9', '888.999.000-00', 'Lucas Martins', 'lucas@example.com'),
    ('b453de70-2a56-4e5a-8f30-bc3c2f0e8c8b', '123.987.456-00', 'Fernanda Silva', 'fernanda@example.com'),
    ('cd9c5b6a-af23-4e01-bef4-5f8945f7b8c1', '321.654.987-00', 'Roberto Sousa', 'roberto@example.com');


INSERT INTO jobs_applicants (job, applicant)
VALUES 
    ('8c8c2278-b9d3-4f0e-b0b7-df8a823e7290', '3e0e8913-89fd-4b0b-8e0c-72f1728bb9f1'),
    ('8c8c2278-b9d3-4f0e-b0b7-df8a823e7290', '4f9ae312-d309-4c17-899f-8f1a7e0729b3'),
    ('b1121e47-9d8b-4eeb-a867-29e1b97d3ff4', '6c2453d7-d49a-48ac-b47c-f0b3c7565db1'),
    ('b1121e47-9d8b-4eeb-a867-29e1b97d3ff4', 'e944f8c1-21b2-4a68-81c6-6a89eab889f3'),
    ('e5b3bf22-25a4-4d2a-8b73-0f50149289bd', 'f7f26f39-0044-4a21-aecc-46d38b8ad05a'),
    ('8c8c2278-b9d3-4f0e-b0b7-df8a823e7290', 'f7f26f39-0044-4a21-aecc-46d38b8ad05a'),
    ('aadaf527-31c4-48b7-a1bf-f03868950d2d', '6fe580a1-3656-4fc7-af3c-38a0dc6f4cd8'),
    ('e8c4b01f-4fc8-493e-8fd5-1c6cf3157250', '8fd4a30f-f676-4fd0-b8ae-f312cb786af7'),
    ('f5b19f9b-9f5b-4c90-9166-bec5db8f2f4c', 'a08f26a9-c9d6-4078-8142-97f96cdd0fe9'),
    ('c3f23ab1-979b-43a7-b75f-6f1bf3a5d77a', 'b453de70-2a56-4e5a-8f30-bc3c2f0e8c8b'),
    ('f1c6f382-6c16-4e67-a869-9337e477b27a', 'cd9c5b6a-af23-4e01-bef4-5f8945f7b8c1');


