import {render, screen, fireEvent} from '@testing-library/react'
import {describe, it, expect, vi} from 'vitest'
import {Button} from '../components/Button.jsx'
import '@testing-library/jest-dom'

describe ('Button component', () => {
    it('button renders correct caption',() => {
        render(<Button caption = 'ok' onClick = {() => {}}/>)
        expect (screen.getByText('ok')).toBeInTheDocument()
    })

    it('calls onClick when clicked', async() => {
        const handleClick = vi.fn()
        render(<Button caption= 'Click Me' onClick={handleClick}/>)
        fireEvent.click(screen.getByText('Click Me')) //trigger the event (user)
        expect (handleClick).toHaveBeenCalledTimes(1)

    })
})



/**
 * jest/vitest : engine
 * testing-library: driver
 * describe - group of all the tests;
 * starts from top level
 */