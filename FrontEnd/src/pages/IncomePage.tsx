import React, { useState, useEffect, ChangeEvent, FormEvent } from 'react';

interface Income {
  id?: number;
  title: string;
  description: string;
  category: string;
  date: string;
  amount: number;
}

const IncomePage: React.FC = () => {
  const [incomes, setIncomes] = useState<Income[]>([]);

  const [formData, setFormData] = useState({
    title: '',
    description: '',
    category: '',
    amount: '',
  });

  useEffect(() => {
    const fetchIncomes = async () => {
      try {
        const response = await fetch('http://localhost:8080/income/all', {
          credentials: 'include',
        });

        if (response.ok) {
          const data = await response.json();
          setIncomes(data);
        } else {
          console.error('Failed to fetch incomes');
        }
      } catch (error) {
        console.error('Error fetching incomes:', error);
      }
    };

    fetchIncomes();
  }, []);

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();

    const newIncome: Income = {
      title: formData.title,
      description: formData.description,
      category: formData.category,
      amount: parseFloat(formData.amount),
      date: new Date().toISOString().split('T')[0],
    };

    try {
      const response = await fetch('http://localhost:8080/income', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include',
        body: JSON.stringify(newIncome),
      });

      if (response.ok) {
        const savedIncome = await response.json();
        setIncomes((prev) => [...prev, savedIncome]);
        setFormData({ title: '', description: '', category: '', amount: '' });
      } else {
        console.error('Failed to add income');
      }
    } catch (error) {
      console.error('Error posting income:', error);
    }
  };

  return (
    <div style={styles.page}>
      <div style={styles.inner}>
        <h1 style={styles.heading}>💰 Your Incomes</h1>
        <table style={styles.table}>
          <thead>
            <tr>
              <th style={styles.th}>Date</th>
              <th style={styles.th}>Title</th>
              <th style={styles.th}>Description</th>
              <th style={styles.th}>Category</th>
              <th style={styles.th}>Amount ($)</th>
            </tr>
          </thead>
          <tbody>
            {incomes.map((income) => (
              <tr key={income.id}>
                <td style={styles.td}>{income.date}</td>
                <td style={styles.td}>{income.title}</td>
                <td style={styles.td}>{income.description}</td>
                <td style={styles.td}>{income.category}</td>
                <td style={styles.td}>${income.amount.toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>

        <form onSubmit={handleSubmit} style={styles.form}>
          <input
            name="title"
            type="text"
            placeholder="Title"
            value={formData.title}
            onChange={handleChange}
            required
            style={styles.input}
          />
          <input
            name="description"
            type="text"
            placeholder="Description"
            value={formData.description}
            onChange={handleChange}
            required
            style={styles.input}
          />
          <input
            name="category"
            type="text"
            placeholder="Category"
            value={formData.category}
            onChange={handleChange}
            required
            style={styles.input}
          />
          <input
            name="amount"
            type="number"
            placeholder="Amount"
            value={formData.amount}
            onChange={handleChange}
            required
            step="0.01"
            style={styles.input}
          />
          <button type="submit" style={styles.button}>Add Income</button>
        </form>
      </div>
    </div>
  );
};

const styles: { [key: string]: React.CSSProperties } = {
  page: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    background: 'linear-gradient(to right, #43e97b, #38f9d7)',
    minHeight: '100vh',
    width: '100vw',
    margin: 0,
    padding: '2rem',
  },
  inner: {
    width: '90%',
    maxWidth: '1000px',
  },
  heading: {
    fontSize: '2.5rem',
    fontWeight: 700,
    marginBottom: '2rem',
    textAlign: 'center',
    color: '#fff',
    textShadow: '1px 2px 4px rgba(0, 0, 0, 0.3)',
  },
  table: {
    width: '100%',
    borderCollapse: 'collapse',
    backgroundColor: 'rgba(255, 255, 255, 0.1)',
    borderRadius: '12px',
    overflow: 'hidden',
    color: '#fff',
    marginBottom: '2rem',
  },
  th: {
    padding: '1rem',
    backgroundColor: '#008F7A',
    textAlign: 'left',
    fontWeight: 600,
  },
  td: {
    padding: '1rem',
    borderBottom: '1px solid rgba(255, 255, 255, 0.2)',
  },
  form: {
    display: 'flex',
    gap: '1rem',
    flexWrap: 'wrap',
    justifyContent: 'center',
  },
  input: {
    padding: '0.5rem',
    fontSize: '1rem',
    borderRadius: '8px',
    border: 'none',
    width: '200px',
  },
  button: {
    padding: '0.5rem 1.5rem',
    fontSize: '1rem',
    backgroundColor: '#008F7A',
    color: '#fff',
    border: 'none',
    borderRadius: '8px',
    cursor: 'pointer',
  },
};

export default IncomePage;
