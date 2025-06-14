import React, { useState, FormEvent } from 'react';

const LoginPage: React.FC = () => {
  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');

  const handleLoginSubmit = async (e: FormEvent) => {
    e.preventDefault();

    const formData = new URLSearchParams();
    formData.append('username', email);
    formData.append('password', password);

    try {
      const response = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        body: formData,
        credentials: 'include',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
      });

      if (response.redirected) {
        window.location.href = response.url;
      } else if (response.ok) {
        alert('Logged in!');
      } else {
        alert('Invalid credentials');
      }
    } catch (err) {
      console.error('Login error:', err);
    }
  };

  const handleGoogleLogin = () => {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  };

  return (
    <div style={styles.pageWrapper}>
      <div style={styles.card}>
        <img src="/logo.png" alt="Finance Tracker Logo" style={styles.logo} />
        <h1 style={styles.title}>Finance Tracker</h1>
        <h2 style={styles.subtitle}>Login to your account</h2>
        <form onSubmit={handleLoginSubmit} style={styles.form}>
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            style={styles.input}
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            style={styles.input}
          />
          <button type="submit" style={styles.loginBtn}>Login</button>
        </form>
        <div style={styles.separator}>or</div>
        <button onClick={handleGoogleLogin} style={styles.googleBtn}>
          <img
            src="https://developers.google.com/identity/images/g-logo.png"
            alt="Google"
            style={styles.googleIcon}
          />
          Sign in with Google
        </button>
      </div>
    </div>
  );
};

const styles: { [key: string]: React.CSSProperties } = {
  pageWrapper: {
    height: '100vh',
    width: '100vw',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f0f2f5',
  },
  card: {
    width: '100%',
    maxWidth: '400px',
    backgroundColor: '#fff',
    padding: '2rem',
    borderRadius: '12px',
    boxShadow: '0 10px 25px rgba(0,0,0,0.1)',
    textAlign: 'center',
  },
  logo: {
    width: '60px',
    marginBottom: '1rem',
  },
  title: {
    fontSize: '1.8rem',
    margin: '0',
    color: '#333',
  },
  subtitle: {
    fontSize: '1rem',
    marginBottom: '1.5rem',
    color: '#666',
  },
  form: {
    display: 'flex',
    flexDirection: 'column',
    gap: '1rem',
  },
  input: {
    padding: '0.75rem',
    fontSize: '1rem',
    borderRadius: '6px',
    border: '1px solid #ccc',
  },
  loginBtn: {
    backgroundColor: '#4CAF50',
    color: 'white',
    border: 'none',
    padding: '0.75rem',
    borderRadius: '6px',
    fontSize: '1rem',
    cursor: 'pointer',
    transition: 'background 0.3s',
  },
  separator: {
    margin: '1.5rem 0',
    color: '#999',
    fontSize: '0.9rem',
  },
  googleBtn: {
    backgroundColor: '#4285F4',
    color: '#fff',
    border: 'none',
    padding: '0.75rem',
    borderRadius: '6px',
    fontSize: '1rem',
    cursor: 'pointer',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    gap: '0.5rem',
  },
  googleIcon: {
    width: '20px',
    height: '20px',
  },
};

export default LoginPage;
