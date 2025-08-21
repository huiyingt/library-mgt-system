import { render, screen, fireEvent } from '@testing-library/react';
import {describe, it, expect, vi} from 'vitest'
import { TodoForm } from '../components/TodoForm';

describe('TodoForm', () => {
  it('submits new todo correctly', () => {
    const mockAddTodo = vi.fn();

    render(<TodoForm addTodo={mockAddTodo} />);

    fireEvent.change(screen.getByLabelText(/title/i), {
      target: { value: 'Test todo' },
    });

    fireEvent.click(screen.getByRole('button', { name: /add/i }));

    expect(mockAddTodo).toHaveBeenCalledOnce();
    expect(mockAddTodo).toHaveBeenCalledWith(
      expect.objectContaining({ title: 'Test todo' })
    );
  });
});
