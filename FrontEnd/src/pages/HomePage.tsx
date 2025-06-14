import React from 'react';
import { useNavigate } from 'react-router-dom';
import{useState, useEffect}from 'react';

const HomePage: React.FC = () => {
  const navigate = useNavigate();

const [income, setIncomeTotal] = useState(0);
const [expense, setExpenseTotal] = useState(0);
const [balance, setBalance] = useState(0);

useEffect(() => {
    const fetchStats = async () => {
      try {
        const response = await fetch('http://localhost:8080/stats', {
          credentials: 'include', // Required if using session cookies
        });

        if (response.ok) {
          const data = await response.json();
          setIncomeTotal(data.income || 0);
          setExpenseTotal(data.expense || 0);
          setBalance(data.balance||0);
        } else {
          console.error('Failed to fetch stats');
        }
      } catch (error) {
        console.error('Error fetching stats:', error);
      }
    };
    fetchStats();
},[]);


const handleIncomeClick = () => {
    navigate('/incomes');
  };
const handleExpenseClick = () => {
    navigate('/expenses');
  };

  const handleDashBoardClick = () => {
    navigate('/dashboard');
  };

  return (
    <div style={styles.page}>
      <header style={styles.header}>
        <h1 style={styles.title}>Finance Tracker Dashboard</h1>
        <p style={styles.subtitle}>Manage your money with ease and style</p>
      </header>

      <main style={styles.main}>
        <section style={styles.summaryCard}
                  onClick={handleDashBoardClick}>
          <h2 style={styles.cardTitle}>💰 Total Balance</h2>
          <p style={styles.cardValue}>${balance.toFixed(2)}</p>
        </section>

        <section style={{ ...styles.summaryCard, backgroundColor: '#FF6F91' }}
                onClick={handleIncomeClick}
                >
          <h2 style={styles.cardTitle}>📈Income</h2>
          <p style={styles.cardValue}>${income.toFixed(2)}</p>
        </section>

        <section style={{ ...styles.summaryCard, backgroundColor: '#FF9671' }}
                    onClick={handleExpenseClick}
                    >
          <h2 style={styles.cardTitle}>💸Expenses</h2>
          <p style={styles.cardValue}>${expense.toFixed(2)}</p>
        </section>
      </main>

      <footer style={styles.footer}>
        <p>© 2025 Finance Tracker. All rights reserved.</p>
      </footer>
    </div>
  );
};

const styles: { [key: string]: React.CSSProperties } = {
  page: {
    height: '100vh',
    width: '100vw',
    background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
    color: '#fff',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    padding: '3rem 2rem',
    boxSizing: 'border-box',
    fontFamily: 'Arial, sans-serif',
  },
  header: {
    textAlign: 'center',
    marginBottom: '3rem',
    textShadow: '0 2px 8px rgba(0,0,0,0.3)',
  },
  title: {
    fontSize: '3rem',
    margin: 0,
    fontWeight: '700',
  },
  subtitle: {
    fontSize: '1.25rem',
    marginTop: '0.5rem',
    fontWeight: '400',
  },
  main: {
    display: 'grid',
    gridTemplateColumns: 'repeat(auto-fit, minmax(250px, 1fr))',
    gap: '2rem',
    width: '100%',
    maxWidth: '960px',
  },
  summaryCard: {
    backgroundColor: '#845EC2',
    borderRadius: '15px',
    padding: '2rem',
    boxShadow: '0 6px 18px rgba(0, 0, 0, 0.25)',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    minHeight: '160px',
    cursor: 'default',
    transition: 'transform 0.3s ease',
  },
  cardTitle: {
    fontSize: '1.3rem',
    marginBottom: '0.8rem',
    fontWeight: '600',
  },
  cardValue: {
    fontSize: '2.25rem',
    fontWeight: '700',
    letterSpacing: '0.05em',
  },
  footer: {
    marginTop: 'auto',
    paddingTop: '2rem',
    fontSize: '0.9rem',
    opacity: 0.7,
    textAlign: 'center',
  },
};

export default HomePage;
